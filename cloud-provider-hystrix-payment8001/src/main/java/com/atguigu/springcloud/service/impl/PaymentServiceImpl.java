package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "Thread: " + Thread.currentThread().getName() + " paymentInfo_OK, id:"
                + id + "\t" + "O(∩_∩)O Yeah!";
    }

    //超时降级演示
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") //5秒钟以内就是正常的业务逻辑
    })


    @Override
    public String payment_Timeout(Integer id) {
        int timeNumber = 3;
//        int timeNumber = 15;
//        int timeNumber = 1 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thread: " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id："
                + id + "\t" + "o(╥﹏╥)o Oops! " + "Time cost in seconds " + timeNumber;
    }

    //兜底方法，上面方法出问题,我来处理，返回一个出错信息
    public String payment_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " payment_TimeoutHandler,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
    }

}
