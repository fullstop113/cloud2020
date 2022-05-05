package com.atguigu.controller;

import com.atguigu.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderHystirxController {
    @Resource
    PaymentHystrixService paymentHystrixService;
    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfoOk(id);
        log.info("*********result"+result);
        return result;
    }
    @GetMapping(value = "/consumer/payment/hystrix/ok/timeout/{id}")
    public String paymentInfoOkTimeout(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfoOkTimeout(id);
        log.info("*********result"+result);
        return result;
    }
}
