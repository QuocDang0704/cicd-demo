package com.example.be_dolan_final.config;

public class Constant {
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MSG = "SUCCESS";
    public static final String FAIL_CODE = "1";
    public static final String FAIL_MSG = "FAIL";

    // VnPay
    public static final String MERCHANT_ID = "E310KJP0";
    public static final String SECURE_SECRET = "PUDDTCNYZGYJEEZKKICDIZQKLECKOLKT";
    public static final String API_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNP_RETURN_URL = "http://localhost:8080/vnpay_return";
    public static final String VNP_ORDER_TYPE = "170000";
    public static final String VNP_Locale = "vn";
    public static final String VNP_IPADDR = "127.0.0.1";
    public static final String VNP_VERSION = "2.1.0";

    public static final String VNP_COMMAND = "pay";
    public static final String VNP_CURR_CODE = "VND";

    public enum OderDetails {
        Success,
        WaitingForReturn, // Trả hàng
        ChangeToPublic, // Đổi hàng

        ExchangeSuccess, // Đổi thành công

        ReturnSuccess, // Trả thành công
    }

    public enum OderStatus {
        WaitForConfirmation,  // Chờ xác nhận
        PreparingGoods, // Chuẩn bị hàng
        Delivery, // Đang giao hàng
        Success, // Thành công
        ChangeOrReturn, // đổi hoặc trả hàng (cho 1 hay nhiều orderDetails)
        Cancel          // hủy đơn hàng
    }

    public enum ExchangesStatus {
        WaitFor,
        Accept,
        Reject
    }

    public enum ExchangesType {
        Change,
        Return
    }

    public enum UserRole {
        ADMIN,
        STAFF,
        CUSTOMER
    }

}


