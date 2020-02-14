package soap.service.impl;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import soap.service.RocketMqService;

import javax.annotation.Resource;

/**
 * Created by ZhangPY on 2020/2/10
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain:
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Resource
    private DefaultMQProducer defaultMQProducer;

    @Value("${rocket.topic}")
    private String topic;

    @Override
    public SendResult openAccountMsg(String msgInfo) {

        SendResult sendResult = null;
        try {
            Message sendMsg = new Message("CicadaTopic", "CicadaTag",
                    msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}
