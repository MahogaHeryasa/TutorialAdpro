package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService{
    public Payment addPayment (Order order, String method, Map<String, String> paymentDetails){return null;}
    public Payment setStatus (Payment payment, String status){return null;}
    public Payment getPayment (String paymentId){return null;}
    public List<Payment> getAllPayments(){return null;}
}
