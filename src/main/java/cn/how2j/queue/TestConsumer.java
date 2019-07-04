package cn.how2j.queue;

import cn.how2j.utils.ActiveMqUtil;
import cn.hutool.core.util.RandomUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

//消息的生产消费情况可以登录ActiveMq图形化界面查看:127.0.0.1:8161,账号密码都是admin
public class TestConsumer {

    //服务器地址
    public static final String url = "tcp://127.0.0.1:61616";
    //这次发送的消息名称,消息模式是队列模式(分食模式)
    public static final String queueName = "queue_style";
    //这次发送的消息名称,消息模式是主题模式(订阅模式)
    public static final String topicName = "topic_style";
    //创建多个消费者
    public static final String consumerName = "Consumer:"+ RandomUtil.randomString(5);

    public static void main(String[] args) throws JMSException{
        ActiveMqUtil.checkServer();
        System.out.printf("%s 消费者启动了。 %n", consumerName);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //这个地方会抛出JMSException异常
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建的是Session的策略
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
//        主题模式
//        Destination destination = session.createTopic(topicName);
        MessageConsumer consumer = session.createConsumer(destination);
        //消息的消费者要创建一个监听器用来监听消费者消费的消息,同时此处建立一个监听器,监听器中的onMesaage()方法也是为了反馈给
//        session策略
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try{
                    System.out.println(consumerName+"接收消息"+textMessage.getText());
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
    }


}
