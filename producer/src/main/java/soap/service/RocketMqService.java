package soap.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * Created by ZhangPY on 2020/2/10
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain:
 */
public interface RocketMqService {

    /**
     * 发送同步消息
     * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
     * @param msgInfo
     * @return
     */
    SendResult sendSynchMessage(String msgInfo);


    /**
     * 发送异步消息
     * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
     * @param msgInfo
     * @return
     */
    void sendAsynchMessage(String msgInfo);


    /**
     * 单向发送消息
     * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
     * @param message
     */
    void sengOnewayMessage(String message);
}
