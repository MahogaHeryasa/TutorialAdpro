package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;


@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method,  Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;

        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else if (method.equals(PaymentMethod.VOUCHER.getValue()) && !validateVoucherCode(paymentData.get("voucherCode"))) {
            throw new IllegalArgumentException();
        } else if (method.equals(PaymentMethod.BANK.getValue()) && !validateNullValues(paymentData)) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }

        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    boolean validateVoucherCode(String voucherCode){
        if (voucherCode.length() != 16) {
            return false;
        }
        String regex = "^ESHOP.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*$";
        return  voucherCode.matches(regex);
    }

    boolean validateNullValues(Map<String, String> map){
        if (map.containsValue(null) || map.containsKey(null) || map.containsKey("") || map.containsValue("")) {
            return false;
        }
        return true;
    }

}
