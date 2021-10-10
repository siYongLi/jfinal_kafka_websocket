package com.demo.controller;

import com.demo.domain.KafkaConfig;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import io.jboot.Jboot;
import io.jboot.aop.annotation.ConfigValue;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

/**
 * @description:
 * @author: lsy
 * @create: 2021-10-09 22:28
 **/
@RequestMapping(value="/", viewPath = "/html")
public class WebsocketController extends JbootController {
    @ConfigValue("undertow.host")
    private String host;

    @ConfigValue("undertow.port")
    private int port;

    @ConfigValue("jboot.redis.host")
    private String redisHost;

    @ConfigValue("jboot.kafka.servers")
    private String kafkaHost;

    private static final List<String> topics = Jboot.config(KafkaConfig.class).getTopics();

    public void index() {
        if (topics.size()==0) {
            renderText("请检测kafka配置");
            return;
        }
        setAttr("address", host+":"+port);
        setAttr("kafkaIp",kafkaHost);
        setAttr("topics",topics);
        setAttr("defaultTopic",topics.get(0));
        render("kafka.html");
    }

    public void redis() {
        setAttr("address", host+":"+port);
        setAttr("redisIp",redisHost);
        render("redis.html");
    }
}
