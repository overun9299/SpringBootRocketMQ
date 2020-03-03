package soap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import soap.service.RocketMqService;

/**
 * Created by ZhangPY on 2020/2/10
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain:
 */
@RestController
public class SendMessageController {

    @Autowired
    private RocketMqService rocketMqService;


    /**
     * 发送同步消息
     * @param message
     */
    @GetMapping(value = "/sendSynchMessage")
    public void sendSynchMessage(String message) {
        rocketMqService.sendSynchMessage(message);
    }

    /**
     * 发送异步消息
     * @param message
     */
    @GetMapping(value = "/sendAsyncMessage")
    public void sendAsyncMessage(String message) {
        rocketMqService.sendAsynchMessage(message);
    }

    /**
     * 单向发送消息
     * @param message
     */
    @GetMapping(value = "/sengOnewayMessage")
    public void sengOnewayMessage(String message) {
        rocketMqService.sengOnewayMessage(message);
    }


}
