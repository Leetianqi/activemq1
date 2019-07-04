package cn.how2j.utils;

import cn.hutool.core.util.NetUtil;

import javax.swing.*;

public class ActiveMqUtil {
    public  static void checkServer(){
        //要想在127.0.0.1:8161上面看到这个过程,必须开启activemq服务(D:\software\middlewareForMessage\apache-activemq-5.15.8-bin\apache-activemq-5.15.8\bin\win64)
        if (NetUtil.isUsableLocalPort(8161)){
            //弹出一个名为“Message”的信息消息对话框(可以在页面上看到的),其实用system也是可以的
            JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
            System.exit(1);
        }
    }
    //test-push
    //test-pull
}
