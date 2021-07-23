package com.activemq.utils;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @author OVAmach
 * @date 2021/6/16
 * 点对点发布消息
 */
@Component
public class Send {
    @Test
    public void testMQProducerQueue() throws Exception {
        //1、创建activemq的连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建session会话对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("single-queue");
        //6、使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //8、发送消息
        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                    try {
                        TextMessage textMessage = session.createTextMessage("消息发送成功" + finalI);
                        producer.send(textMessage);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            //9、关闭资源
            producer.close();
            session.close();
            connection.close();
        }
    }

    @Test
    public void TestTopicProducer() throws Exception{
        //1、创建工厂连接对象，
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Topic topic = session.createTopic("many-topic");
        //6、使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(topic);
        //7、使用会话对象创建一个消息对象
        Message message = session.createTextMessage("cxyf");
        //8、发送消息
        producer.send(message);
        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}