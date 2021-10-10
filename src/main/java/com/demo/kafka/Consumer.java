package com.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @description: 启用main方法消费
 * @author: lsy
 * @create: 2021-09-25 20:10
 **/
public class Consumer {
    static String BOOTSTRAP_SERVERS="192.168.200.4:9092";

    static String TOPIC="game";

    static String GROUPID="test-consumer-group";
    
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        properties.put("group.id", GROUPID);
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList(TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(2000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }

    }
}
