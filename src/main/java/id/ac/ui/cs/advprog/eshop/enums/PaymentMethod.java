package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("VOUCHER"),
    BANK("BANK");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod methodStatus : PaymentMethod.values()) {
            if (methodStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }

}