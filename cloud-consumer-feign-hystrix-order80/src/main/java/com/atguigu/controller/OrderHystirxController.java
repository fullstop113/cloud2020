package com.atguigu.controller;

import com.atguigu.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
//@DefaultProperties(defaultFallback = "globalFallback")
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
//    @HystrixCommand(fallbackMethod = "paymentInfoOkTimeoutFallback",
//    commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")}
//    )
    @HystrixCommand
    public String paymentInfoOkTimeout(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfoOkTimeout(id);
        log.info("*********result"+result);
        return result;
    }
    public String paymentInfoOkTimeoutFallback(@PathVariable("id")Integer id){
        return "很遗憾，网络原因，服务超时o(╥﹏╥)o";
    }
    public String globalFallback() {

        return "这里是消费端全局globalFallback";
    }
}
