package soap.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * Created by ZhangPY on 2020/2/10
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain:
 */
public interface RocketMqService {

    SendResult openAccountMsg(String msgInfo);
}
