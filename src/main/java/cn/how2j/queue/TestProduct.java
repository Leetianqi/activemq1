package cn.how2j.queue;

import cn.how2j.utils.ActiveMqUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//消息的生产消费情况可以登录ActiveMq图形化界面查看:127.0.0.1:8161,账号密码都是admin
public class TestProduct {

    //服务地址，端口默认61616
    public static final String url = "tcp://127.0.0.1:61616";
    //这次发送的消息名称,消息模式是队列模式(分食模式)
    public static final String queueName = "queue_style";
    //这次发送的消息名称,消息模式是主题模式(订阅模式)
    public static final String topicName = "topic_style";

    public static void main(String[] args) throws JMSException{
        //判断activemq是否启动
        ActiveMqUtil.checkServer();
        //绑定地址
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建回话session,
        //第一个参数:false表示不支持事务
        //第二个参数:Session.AUTO_ACKNOWLEDGE,当客户端从 receive 或 onMessage成功返回时，Session 自动签收客户端的这条消息的收条。
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列类型,queue_style表示队列模式
        Destination destination = session.createQueue(queueName);
        //创建队列类型,topic_style表示主题模式
//        Destination destination = session.createTopic(topicName);
        //创建一个生产者,(创建一个队列模式的生产者)
        MessageProducer producer =session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("队列消息-"+i);
            producer.send(textMessage);
            System.out.println("发送:"+textMessage.getText());
        }
        connection.close();
    }

}
