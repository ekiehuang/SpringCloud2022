package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "Thread: " + Thread.currentThread().getName() + " paymentInfo_OK, id:"
                + id + "\t" + "Yeah!";
    }

    @Override
    public String payment_Timeout(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thread: " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id："
                + id + "\t" + "Oops! " + "Time cost in seconds " + timeNumber;
    }
}
