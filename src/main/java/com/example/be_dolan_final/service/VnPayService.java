package com.example.be_dolan_final.service;

import com.example.be_dolan_final.dto.OrderCreateRequest;
import com.example.be_dolan_final.utils.VnPayUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.be_dolan_final.config.Constant.*;
import static java.nio.charset.StandardCharsets.UTF_8;

@AllArgsConstructor
@Service
public class VnPayService {

    public String createOrder(OrderCreateRequest orderCreateRequest) throws IOException {
        Map<String, String> params = setParamVnpayMap(orderCreateRequest);
        StringBuilder hashData = generationUrlParams(params);
        String secretKey = VnPayUtils.hmacSHA512(SECURE_SECRET, hashData.toString());
        params.put("vnp_SecureHash", secretKey);
        String urlParams = toUrlParams(params);
        return API_URL + "?" + urlParams;
    }

    @NotNull
    private StringBuilder generationUrlParams(Map<String, String> params) throws UnsupportedEncodingException {
        List fieldNames = new ArrayList(params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, UTF_8));
                //Build query
                query.append(URLEncoder.encode(fieldName, UTF_8));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, UTF_8));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        return hashData;
    }

    @NotNull
    private Map<String, String> setParamVnpayMap(OrderCreateRequest orderCreateRequest) {
        String orderId = UUID.randomUUID().toString();
        // String secretKey = generateSecretKey(SECURE_SECRET, orderId, amount);
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Amount", String.valueOf(orderCreateRequest.getAmount() * 100));
        params.put("vnp_Command", VNP_COMMAND);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String current_time = formatter.format(new Date());
        params.put("vnp_CreateDate", current_time);
        params.put("vnp_CurrCode", VNP_CURR_CODE);
        params.put("vnp_IpAddr", VNP_IPADDR);
        params.put("vnp_Locale", VNP_Locale);
        params.put("vnp_OrderInfo", orderCreateRequest.getOrderInfo());
        params.put("vnp_OrderType", VNP_ORDER_TYPE);
        params.put("vnp_ReturnUrl", orderCreateRequest.getReturnUrl());
        params.put("vnp_TmnCode", MERCHANT_ID);
        params.put("vnp_TxnRef", orderId);
        params.put("vnp_Version", VNP_VERSION);
        return params;
    }

    private String toUrlParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), UTF_8));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), UTF_8));
        }
        return sb.toString();
    }

    private String generateSecretKey(String secureSecret, String orderId, String amount) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secureSecret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String input = "vnp_Amount=" + amount
                    + "&vnp_Command=pay&vnp_CreateDate=20230417120000&vnp_CurrCode=VND&vnp_IpAddr=127.0.0.1&vnp_Locale=vn&vnp_OrderInfo=Order info&vnp_OrderType=100000&vnp_ReturnUrl=http://localhost:8080/vnpay-return&vnp_TmnCode="
                    + MERCHANT_ID + "&vnp_TxnRef=" + orderId + "&vnp_Version=2.0.0&" + SECURE_SECRET;
            byte[] bytes = sha256_HMAC.doFinal(input.getBytes());
            String hash = Hex.encodeHexString(bytes);
            return hash.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}