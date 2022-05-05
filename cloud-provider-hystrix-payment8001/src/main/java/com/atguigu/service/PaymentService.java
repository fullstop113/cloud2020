package com.atguigu.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfoOk(Integer id){
        return "线程池：  "+Thread.currentThread().getName()+"  paymentInfoOk,id: "+id+"\t O(∩_∩)O哈哈~";
    }

    public String paymentInfoOkTimeout(Integer id){
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  "+Thread.currentThread().getName()+"  paymentInfoTimeout,id: "+id+"\t O(∩_∩)O哈哈~ 耗时3s";
    }
}
