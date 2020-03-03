package soap.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZhangPY on 2020/2/9
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: 消息消费监听(push模式)
 */
@Component
public class RocketMsgListener implements MessageListenerConcurrently {

    private static final Logger LOG = LoggerFactory.getLogger(RocketMsgListener.class) ;

    @Value("${rocket.topic}")
    private String topic;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {

       
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        LOG.info("----接受到的消息为："+new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume ==3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        if(messageExt.getTopic().equals(topic)){
            String tags = messageExt.getTags() ;
            switch (tags){
                case "Game":
                    LOG.info("----游戏 tag == >>"+tags);
                    break ;
                default:
                    LOG.info("----未匹配到Tag == >>"+tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
