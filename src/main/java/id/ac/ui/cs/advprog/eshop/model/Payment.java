package id.ac.ui.cs.advprog.eshop.model;

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
        } else if (method.equals("voucher") && !validateVoucherCode(paymentData.get("voucherCode"))) {
            throw new IllegalArgumentException();
        } else if (method.equals("bank") && !validateNullValues(paymentData)) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }

        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    public void setStatus(String status) {
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
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
