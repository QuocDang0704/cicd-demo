package com.example.be_dolan_final.service;

import com.example.be_dolan_final.config.Constant;
import com.example.be_dolan_final.config.exception.NotFoundException;
import com.example.be_dolan_final.dto.CancelOrderDTO;
import com.example.be_dolan_final.dto.OrderDTO;
import com.example.be_dolan_final.dto.OrderDetailsDTO;
import com.example.be_dolan_final.entities.*;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.*;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.example.be_dolan_final.config.Constant.ExchangesType.Change;
import static com.example.be_dolan_final.config.Constant.ExchangesType.Return;
import static com.example.be_dolan_final.config.Constant.OderDetails.*;
import static com.example.be_dolan_final.config.Constant.OderStatus.ChangeOrReturn;
import static com.example.be_dolan_final.config.Constant.OderStatus.Success;
import static com.example.be_dolan_final.config.Constant.UserRole.ADMIN;


@AllArgsConstructor
@Service
public class OrderService {

    private final IOrderRepository orderRepository;

    private final IOrderDetailsRepository orderDetailsRepository;

    private final IUserRepository userRepository;

    private final UserService userService;

    private final IProductDetailRepository productDetailRepository;
    private final IProductRepository productRepository;

    private final IVouchersRepository vouchersRepository;

    private final IAddressRepository addressRepository;

    private final IExchangeRepository exchangeRepository;

    private final IExchangeDetailsRepository exchangeDetailsRepository;

    public Page<OrderDTO> getOrderByStatus(String status, String phone, Pageable pageable) {
        User currentUser = this.userService.getCurrentUser();
        if (ADMIN.equals(currentUser.getRole())) {
            return MapperUtils.mapEntityPageIntoDtoPage(this.orderRepository.getOrdersByStatusAdmin(status, phone, pageable), OrderDTO.class);
        } else {
            return MapperUtils.mapEntityPageIntoDtoPage(this.orderRepository.getOrdersByStatusUser(currentUser.getId(), status, pageable), OrderDTO.class);
        }
    }

    public OrderDTO getOne(Long id) {
        Orders order = this.orderRepository.findByIdOrThrow(id, "Order not found");
        List<OrderDetails> orderDetails = this.orderDetailsRepository.findAllByOrderId(order.getId());
        User user = this.userRepository.findById(order.getUserId()).orElse(null);
        OrderDTO orderDTO = MapperUtils.map(order, OrderDTO.class);
        orderDTO.setNameUser(user.getFirstName() + " " + user.getLastName());
        orderDTO.setOrderDetailsDTOS(MapperUtils.mapList(orderDetails, OrderDetailsDTO.class));
        orderDTO.getOrderDetailsDTOS().forEach(x -> {
            ProductDetails productDetails = this.productDetailRepository.findByIdOrThrow(x.getProductDetailsId());
            Products products = productRepository.findByIdOrThrow(productDetails.getProductId());
            x.setImage(products.getImage());
            x.setName(products.getName());
        });

        return orderDTO;
    }

    public OrderDTO getOrderByCode(String code) {
        return MapperUtils.map(this.orderRepository.findFirstByCode(code), OrderDTO.class);
    }

    public OrderDTO createdOrder(OrderDTO orderDTO) {
        Long id = this.userService.getCurrentUser().getId();
        orderDTO.setStatus(Constant.OderStatus.WaitForConfirmation);
        orderDTO.setUserId(id);
        Orders order = this.orderRepository.save(MapperUtils.map(orderDTO, Orders.class));
        orderDTO.getOrderDetailsDTOS().forEach(x -> {
            x.setOrderId(order.getId());
            x.setStatus(Constant.OderDetails.Success);
        });
        if (orderDTO.getVoucherId() > 0) {
            Vouchers vouchers = this.vouchersRepository.findByIdOrThrow(orderDTO.getVoucherId(), "voucher not found");
            vouchers.setSlot(vouchers.getSlot() - 1);
            this.vouchersRepository.save(vouchers);
            return getOrderDTO(orderDTO);
        } else {
            return getOrderDTO(orderDTO);
        }
    }

    @NotNull
    private OrderDTO getOrderDTO(OrderDTO orderDTO) {
        List<OrderDetails> orderDetails = this.orderDetailsRepository.saveAll(MapperUtils.mapList(orderDTO.getOrderDetailsDTOS(), OrderDetails.class));
        List<Long> orderDetailsId = orderDetails.stream().map(OrderDetails::getProductDetailsId).collect(Collectors.toList());
        List<ProductDetails> productDetails = this.productDetailRepository.findAllById(orderDetailsId);
        List<ProductDetails> productDetailsList = Lists.newArrayList();
        productDetails.forEach(x -> {
            orderDetails.forEach(y -> {
                if (y.getProductDetailsId().equals(x.getId())) {
                    x.setQuantity(x.getQuantity() - y.getQuantity());
                    productDetailsList.add(x);
                }
            });
        });
        this.productDetailRepository.saveAll(productDetailsList);
        return orderDTO;
    }

    public OrderDTO updateStatus(OrderDTO orderDTO) {
        this.orderRepository.findById(orderDTO.getId()).map(orders -> {
            orders.setStatus(orderDTO.getStatus());
            return this.orderRepository.save(orders);
        }).orElseThrow(() -> new NotFoundException("Order not found"));
        return orderDTO;
    }

    // Bỏ
//    public void exchangeUpdateOrderDetails(Long orderId, Long orderDetailId) {
//        Exchanges exchanges = this.exchangeRepository.findFirstByOrderId(orderId).orElseThrow(() -> new RuntimeException("Exchange not found"));
//        OrderDetails orderDetails = this.orderDetailsRepository.findByIdOrThrow(orderDetailId, "OrderDetails not found");
//        if (exchanges.getOrderId().equals(orderDetails.getOrderId())) {
//            ExchangeDetails exchangeDetails = this.exchangeDetailsRepository.findFirstByOrderDetailId(orderDetails.getId()).orElseThrow(() -> new RuntimeException("ExchangeDetails not found"));
//            orderDetails.setQuantity(orderDetails.getQuantity() - exchangeDetails.getQuantity());
//            this.orderDetailsRepository.save(orderDetails);
//            this.orderDetailsRepository.save(
//                    OrderDetails.builder()
//                            .orderId(exchanges.getOrderId())
//                            .productDetailsId(orderDetails.getProductDetailsId())
//                            .price(orderDetails.getPrice())
//                            .quantity(exchangeDetails.getQuantity())
//                            .status(ChangeToPublic).
//                            build()
//            );
//        }
//    }

    public void exchangeUpdateOrderDetailsList(Long orderId, List<Long> orderDetailId) {
        Exchanges exchanges = this.exchangeRepository.findFirstByOrderId(orderId).orElseThrow(() -> new RuntimeException("Exchange not found"));
        List<OrderDetails> orderDetails = this.orderDetailsRepository.findAllById(orderDetailId);
        orderDetails.forEach(x -> {
            if (x.getOrderId().equals(exchanges.getOrderId())) {
                if (Change.equals(exchanges.getType())) {
                    ExchangeDetails exchangeDetails = this.exchangeDetailsRepository.findFirstByOrderDetailId(x.getId()).orElseThrow(() -> new RuntimeException("ExchangeDetails not found"));
                    x.setQuantity(x.getQuantity() - exchangeDetails.getQuantity());
                    this.orderDetailsRepository.save(x);
                    this.orderDetailsRepository.save(
                            OrderDetails.builder()
                                    .orderId(exchanges.getOrderId())
                                    .productDetailsId(x.getProductDetailsId())
                                    .price(x.getPrice())
                                    .quantity(exchangeDetails.getQuantity())
                                    .status(ChangeToPublic).
                                    build()
                    );
                    Orders orders = this.orderRepository.findByIdOrThrow(exchanges.getOrderId(), "Order not found");
                    orders.setStatus(ChangeOrReturn);
                    this.orderRepository.save(orders);
                } else if (Return.equals(exchanges.getType())) {
                    ExchangeDetails exchangeDetails = this.exchangeDetailsRepository.findFirstByOrderDetailId(x.getId()).orElseThrow(() -> new RuntimeException("ExchangeDetails not found"));
                    x.setQuantity(x.getQuantity() - exchangeDetails.getQuantity());
                    this.orderDetailsRepository.save(x);
                    this.orderDetailsRepository.save(
                            OrderDetails.builder()
                                    .orderId(exchanges.getOrderId())
                                    .productDetailsId(x.getProductDetailsId())
                                    .price(x.getPrice())
                                    .quantity(exchangeDetails.getQuantity())
                                    .status(WaitingForReturn).
                                    build()
                    );
                    Orders orders = this.orderRepository.findByIdOrThrow(exchanges.getOrderId(), "Order not found");
                    orders.setStatus(ChangeOrReturn);
                    this.orderRepository.save(orders);
                }
            }
        });
    }

    public void cancelOderSucess(CancelOrderDTO cancelOrderDTO) {
        Orders orders = this.orderRepository.findByIdOrThrow(cancelOrderDTO.getOrderId());
        Exchanges exchanges = this.exchangeRepository.findFirstByOrderId(cancelOrderDTO.getOrderId()).orElseThrow(() -> new RuntimeException("Exchange not found"));
        AtomicReference<Double> sum = new AtomicReference<>((double) 0);
        if (Change.equals(exchanges.getType())) {
            cancelOrderDTO.getOrderDetailsDTOS().forEach(orderDetails -> {
                OrderDetails byIdOrThrow = this.orderDetailsRepository.findByIdOrThrow(orderDetails.getId());
                byIdOrThrow.setQuantity(orderDetails.getQuantity());
                this.orderDetailsRepository.save(byIdOrThrow);
            });
            cancelOrderDTO.getListDelete().forEach(this.orderDetailsRepository::deleteById);
            orders.setStatus(Success);
            this.orderRepository.save(orders);
        } else if (Return.equals(exchanges.getType())) {
            cancelOrderDTO.getOrderDetailsDTOS().forEach(orderDetails -> {
                OrderDetails byIdOrThrow = this.orderDetailsRepository.findByIdOrThrow(orderDetails.getId());
                byIdOrThrow.setQuantity(orderDetails.getQuantity());
                this.orderDetailsRepository.save(byIdOrThrow);
            });
            cancelOrderDTO.getListDelete().forEach(this.orderDetailsRepository::deleteById);
            orders.setStatus(Success);
            this.orderRepository.save(orders);
        }
    }

    public void confirmOderSucess(Long orderId, String money) {
        Orders orders = this.orderRepository.findByIdOrThrow(orderId, "order not found");
        if (money.equals("")) {
            orders.setStatus(Success);
            this.orderRepository.save(orders);
        } else {
            orders.setStatus(Success);
            double total = Double.parseDouble(String.valueOf(orders.getTotal()));
            double totalRequest = Double.parseDouble(money);
            double sum = total - totalRequest;
            orders.setTotal(BigDecimal.valueOf(sum));
            this.orderRepository.save(orders);
        }
    }

}
