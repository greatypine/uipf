package com.gasq.bdp.logn.service;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.model.InitProperties;

import java.time.LocalDateTime;

/**
 * 
 * @author Ju_weigang
 * @时间 2018年6月4日下午3:43:05
 * @项目路径 com.gasq.bdp.logn.service
 * @描述
 */
@Service
public class CustomerRest {

    /**
     * 监听方
     */
    //监听注解
    @JmsListener(destination = InitProperties.Moniter_USER)
    public void getQueue(String info) {
        System.out.println("成功监听test消息队列，传来的值为:" + info);
    }

    //
    @JmsListener(destination = InitProperties.BACK_SUBSCRIBE_MSG)
    public void getDelayQueue(String info) {
        System.out.println("成功监听test消息队列，传来的值为:" + info + "当前时间为" + LocalDateTime.now());
    }
}