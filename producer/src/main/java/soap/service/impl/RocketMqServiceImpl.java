package soap.service.impl;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import soap.service.RocketMqService;



/**
 * Created by ZhangPY on 2020/2/10
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain:
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {

    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(RocketMqServiceImpl.class);

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Value("${rocket.topic}")
    private String topic;

    @Value("${rocket.tag}")
    private String tag;


    /**
     * 发送同步消息
     * @param msgInfo
     * @return
     */
    @Override
    public SendResult sendSynchMessage(String msgInfo) {
        SendResult sendResult = null;
        for (int i = 0; i < 10; i++) {
            msgInfo = msgInfo +i;
            try {
                /** 创建消息，并指定Topic，Tag和消息体 */
                Message sendMsg = new Message(topic, tag, msgInfo.getBytes());

                /** 发送同步消息 */
                sendResult = defaultMQProducer.send(sendMsg);
            } catch (Exception e) {
                logger.error("发送同步消息失败" + e.getMessage());
            }
            logger.info("发送同步消息" + sendResult);
        }

        return sendResult ;
    }

    /**
     * 发送异步消息
     * @param msgInfo
     * @return
     */
    @Override
    public void sendAsynchMessage(String msgInfo) {

        try {
            /** 创建消息，并指定Topic，Tag和消息体 */
            Message sendMsg = new Message(topic, tag, msgInfo.getBytes());

            /** SendCallback接收异步返回结果的回调 */
            defaultMQProducer.send(sendMsg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("发送异步消息" + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    logger.error("发送异步消息失败" + throwable);
                }
            });
        } catch (Exception e) {
            logger.error("发送异步消息失败" + e.getMessage());
        }

    }

    /**
     * 发送单向消息
     * @param message
     */
    @Override
    public void sengOnewayMessage(String message) {
        try {
            /** 创建消息，并指定Topic，Tag和消息体 */
            Message sendMsg = new Message(topic, tag, message.getBytes());

            /** 发送单向消息 */
            defaultMQProducer.sendOneway(sendMsg);
        } catch (Exception e) {
            logger.error("发送同步消息失败" + e.getMessage());
        }
    }


}
