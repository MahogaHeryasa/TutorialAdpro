package id.ac.ui.cs.advprog.eshop.model;

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
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher" , emptyPaymentData, "SUCCESS");
        });
    }

    @Test
    void testCreatePaymentValid() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                paymentDataPossibility.getFirst(), "SUCCESS");

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
                this.paymentDataPossibility.getFirst(), "SUCCESS");
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentValidBank() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "bank",
                this.paymentDataPossibility.get(2), "SUCCESS");
        assertEquals("BCA", payment.getPaymentData().get("bankName"));
        assertEquals("12A127DJAK", payment.getPaymentData().get("referenceCode"));
    }

    @Test
    void testCreatePaymentInvalidVoucher() {
        Map<String, String> voucherDataInvalid = new HashMap<>();
        voucherDataInvalid.put("voucherCode", "SHOP1234ABC567");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher", voucherDataInvalid, "SUCCESS");
        });
    }

    @Test
    void testCreatePaymentInvalidBank() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "bank", this.paymentDataPossibility.get(3), "SUCCESS");
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                paymentDataPossibility.getFirst(), "SUCCESS");
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "voucher",
                paymentDataPossibility.getFirst(), "SUCCESS");
        assertThrows (IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
}
