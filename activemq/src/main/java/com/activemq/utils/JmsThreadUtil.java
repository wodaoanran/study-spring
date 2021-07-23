package com.activemq.utils;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;


@Component
public class JmsThreadUtil {

    public void test() throws Exception {
        //1、创建工厂连接对象，
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Topic topic = session.createTopic("many-topic");
        //6、使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(topic);
        for (int i1 = 0; i1 < 10; i1++) {
            int finalI = i1;
            new Thread(() -> {
                try {
                    Thread.sleep(5000);//睡眠5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "处理发送消息" + finalI);
                try {
                    Message message = session.createTextMessage("你好:" + Thread.currentThread().getName() + "的消息" + finalI);
                    producer.send(message);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}