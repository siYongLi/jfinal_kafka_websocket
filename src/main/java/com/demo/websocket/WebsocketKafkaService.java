package com.demo.websocket;

import com.demo.domain.KafkaConfig;
import com.demo.kafka.KafkaService;
import io.jboot.Jboot;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: lsy
 * @create: 2021-10-09 22:27
 **/
@ServerEndpoint("/kafka.ws/{topic}")
public class WebsocketKafkaService {

    @OnMessage
    public void message(String message, Session session) {
        String topic = session.getPathParameters().get("topic");
        String[] messages = message.split("\n");
        for (String msg : messages) {
            if (StringUtils.isEmpty(msg)) {
                continue;
            }
            try {
                KafkaService.getSingleton().send(new ProducerRecord<String, String>(topic, msg));
                session.getAsyncRemote().sendText("推送成功: "+ msg);
            } catch (Exception e) {
                session.getAsyncRemote().sendText("推送失败: "+ msg);
            }
        }
    }
}
