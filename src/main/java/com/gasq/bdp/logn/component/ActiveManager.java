package com.gasq.bdp.logn.component;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.gasq.bdp.logn.utils.ActiveMQUtil;


/**
 * 
 * @author Ju_weigang
 * @时间 2018年6月4日下午3:43:52
 * @项目路径 com.gasq.bdp.logn.component
 * @描述
 */
@Component
public class ActiveManager {
	
	@Value("${spring.activemq.broker-url}")
	private String tcp;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;


    /**
     * @param data
     * @desc 即时发送
     */
    public void sendBack(Destination destination,String data) {
        this.jmsMessagingTemplate.convertAndSend(destination, data);
    }
    
    public void sendForeEnd(String topic, String message) {
    	ActiveMQUtil.sendMessage(tcp, topic, message);
    }

    /**
     * @desc 延时发送
     */
    public void delaySendBack(String text, String queueName, Long time) {
        //获取连接工厂
        ConnectionFactory connectionFactory = this.jmsMessagingTemplate.getConnectionFactory();
        try {
            //获取连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(text);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}