package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class PaymentTest {

    private final List<Map<String, String>> paymentDataPossibility = new ArrayList<>();
    @BeforeEach
    void setUp() {
        Map<String, String> voucherDataValid = new HashMap<>();
        voucherDataValid.put("voucherCode", "ESHOP1234ABC5678");
        paymentDataPossibility.add(voucherDataValid);
        Map<String, String> voucherDataInvalid = new HashMap<>();
        voucherDataInvalid.put("voucherCode", "SHOP1234ABC567");
        paymentDataPossibility.add(voucherDataInvalid);
        Map<String, String> bankDataValid = new HashMap<>();
        bankDataValid.put("bankName", "BCA");
        bankDataValid.put("referenceCode", "12A127DJAK");
        paymentDataPossibility.add(bankDataValid);
        Map<String, String> bankDataInvalid = new HashMap<>();
        bankDataInvalid.put("bankName", null);
        bankDataInvalid.put("referenceCode", null);
        paymentDataPossibility.add(bankDataInvalid);
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        Map<String, String> emptyPaymentData = new HashMap<>();

        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher" , emptyPaymentData, PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    void testCreatePaymentValid() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                paymentDataPossibility.getFirst(), PaymentStatus.SUCCESS.getValue());

        assertSame(paymentDataPossibility.getFirst(), payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("voucher", payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                    paymentDataPossibility.getFirst(), "MEOW");
        });
    }

    @Test
    void testCreatePaymentValidVoucher() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                this.paymentDataPossibility.getFirst(), PaymentStatus.SUCCESS.getValue());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentValidBank() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "bank",
                this.paymentDataPossibility.get(2), PaymentStatus.SUCCESS.getValue());
        assertEquals("BCA", payment.getPaymentData().get("bankName"));
        assertEquals("12A127DJAK", payment.getPaymentData().get("referenceCode"));
    }

    @Test
    void testCreatePaymentInvalidVoucher() {
        Map<String, String> voucherDataInvalid = new HashMap<>();
        voucherDataInvalid.put("voucherCode", "SHOP1234ABC567");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher", voucherDataInvalid, PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    void testCreatePaymentInvalidBank() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "bank", this.paymentDataPossibility.get(3), PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                paymentDataPossibility.getFirst(), PaymentStatus.SUCCESS.getValue());
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                paymentDataPossibility.getFirst(), PaymentStatus.SUCCESS.getValue());
        assertThrows (IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
}