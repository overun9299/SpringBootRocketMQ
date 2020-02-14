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

    @GetMapping(value = "/sendMessage")
    public void sendMessage(String message) {
        rocketMqService.openAccountMsg(message);
    }
}
