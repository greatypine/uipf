//package com.gasq.bdp.logn.utils;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.apache.log4j.Logger;
//
//public class ActiveMQUtil {
//	protected static Logger logger = Logger.getLogger(ActiveMQUtil.class);
//	/**
//     * 发送消息至mq
//     * @param topic     主题
//     * @param message   消息内容
//     */
//    public static void sendMessage(String tcp,String topic, String message){
//        ConnectionFactory connectionFactory; 
//        Connection connection = null;
//        Session session = null; 
//        Destination destination;
//        MessageProducer producer;
//        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
//        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,tcp);
//        try { // 构造从工厂得到连接对象
//            connection = connectionFactory.createConnection();
//            // 启动
//            connection.start();
//            // 获取操作连接
//            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE); 
//            destination = session.createTopic(topic);
//            // 得到消息生成者【发送者】
//            producer = session.createProducer(destination);   
//            // 设置不持久化，实际根据项目决定
////            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//            // 构造消息
//            TextMessage textMsg = session.createTextMessage();
//            textMsg.setText(message);
//            producer.send(destination, textMsg);
//            session.commit();
//        } catch (Exception e) {
//        	logger.info("发送消息失败！错误信息："+e.getMessage(), e);
//        } finally {
//            try {
//                if (null != connection) {
//                	session.close();
//                	connection.close();
//                }
//            } catch (Throwable ignore) {
//            	logger.info("关闭消息链接失败！错误信息："+ignore.getMessage(), ignore);
//            }
//        }
//    }
//    
//    public static Destination getTopicDestination(String name) {
//    	Destination destination = new ActiveMQTopic(name);
//    	return destination;
//    }
//    public static Destination getQueueDestination(String name) {
//    	Destination destination = new ActiveMQQueue(name);
//    	return destination;
//    }
//}