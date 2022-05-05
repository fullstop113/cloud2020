package com.atguigu.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
//@DefaultProperties(defaultFallback = "globalFallback")
public class PaymentService {

    public String paymentInfoOk(Integer id){
        return "线程池：  "+Thread.currentThread().getName()+"  paymentInfoOk,id: "+id+"\t O(∩_∩)O哈哈~";
    }
    @HystrixCommand(fallbackMethod = "paymentInfoOkTimeoutHandler",commandProperties =
            {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")}
    )
//    @HystrixCommand
    public String paymentInfoOkTimeout(Integer id){
        int i = 10 / 0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  "+Thread.currentThread().getName()+
                "  paymentInfoTimeout,id: "+id+"\t O(∩_∩)O哈哈~ 耗时"+timeNumber+"秒";
    }

    public String paymentInfoOkTimeoutHandler(Integer id) {

        return "线程池：  " + Thread.currentThread().getName() + "  paymentInfoTimeout,id: " + id + "\t o(╥﹏╥)o 失败";
    }
    public String globalFallback() {

        return "这里是服务端全局globalFallback";
    }
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
