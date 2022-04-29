package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private PaymentService paymentService;
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*********插入结果",result);
        if (result>0){
            return new CommonResult<>(200,"插入数据成功,serverport:"+serverPort,result);
        }else {
            return new CommonResult<>(444,"插入数据失败",result);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getByPaymentId(@PathVariable long id){
        Payment payment = paymentService.getByPaymentId(id);
        log.info("*********插入结果",payment);
        if (payment!=null){
            return new CommonResult<>(200,"成功,serverport:"+serverPort,payment);
        }else {
            return new CommonResult<>(444,"没有对应记录，查询id："+id,null);
        }
    }
    @GetMapping("/payment/dicovery")
    public DiscoveryClient discovery(){
        return this.discoveryClient;
    }

}
