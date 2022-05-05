package com.atguigu.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id);
    @GetMapping(value = "/payment/hystrix/ok/timeout/{id}")
    public String paymentInfoOkTimeout(@PathVariable("id")Integer id);
}
