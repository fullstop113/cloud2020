package com.atguigu.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "PaymentFallbackServece 兜底中";
    }

    @Override
    public String paymentInfoOkTimeout(Integer id) {
        return "PaymentFallbackServece 兜底中";
    }
}
