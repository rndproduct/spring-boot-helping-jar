package com.brother.paypal.util;

public final class Constant {
    private Constant() {

    }

    public static final String SUCCESS_END_POINT = "/pay/success";
    public static final String CANCEL_END_POINT = "/pay/cancel";
    public static final String SUCCESS_URL = "payment-management" + SUCCESS_END_POINT;
    public static final String CANCEL_URL = "payment-management" + CANCEL_END_POINT;
}
