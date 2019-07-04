package cn.how2j.utils;

import cn.hutool.core.util.NetUtil;

import javax.swing.*;

public class ActiveMqUtil {
    public  static void checkServer(){
        if (NetUtil.isUsableLocalPort(8161)){
            //弹出一个名为“Message”的信息消息对话框
            JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
            System.exit(1);
        }
    }
    //test-push
    //test-pull
}
