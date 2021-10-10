package com.demo.domain;

import io.jboot.app.config.annotation.ConfigModel;

import java.util.List;

/**
 * @description:
 * @author: lsy
 * @create: 2021-10-10 12:10
 **/

@ConfigModel(prefix="jboot.kafka")
public class KafkaConfig {
    private String servers;

    private List<String> topics;

    private String groupid;

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
