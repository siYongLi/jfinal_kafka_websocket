package com.demo.websocket;

import com.alibaba.fastjson.JSONObject;
import com.demo.kafka.KafkaService;
import io.jboot.Jboot;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.GetRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @description:
 * @author: lsy
 * @create: 2021-10-09 22:27
 **/
@ServerEndpoint("/redis.ws")
public class WebsocketRedisService {

    @OnMessage
    public void message(String message, Session session) {
        String[] messages = message.split("\n");
        for (String msg : messages) {
            if (StringUtils.isEmpty(msg)) {
                continue;
            }
            try {
                Object result = Jboot.getRedis().get(msg);
                if (result!=null) {
                    session.getAsyncRemote().sendText("查询结果 : "+ msg+"= "+ result.toString());
                }else{
                    session.getAsyncRemote().sendText("查询结果 : "+ msg+"= null");
                }
            } catch (Exception e) {
                session.getAsyncRemote().sendText("查询异常:"+ msg);
            }
        }
    }
}
