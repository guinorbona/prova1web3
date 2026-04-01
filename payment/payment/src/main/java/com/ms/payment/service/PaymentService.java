package com.ms.payment.service;

import com.ms.payment.dto.PaymentRequest;
import com.ms.payment.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest request);
}