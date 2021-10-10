package com.demo.kafka;

import com.jfinal.aop.Singleton;
import io.jboot.Jboot;
import io.jboot.aop.annotation.Bean;
import io.jboot.service.JbootServiceBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @description:
 * @author: lsy
 * @create: 2021-09-25 20:10
 **/

public class KafkaService {
    private static KafkaProducer producer;

    private KafkaService(){}

    public static KafkaProducer getSingleton() {
        if (producer == null) {
            synchronized (KafkaService.class) {
                if (producer == null) {
                    producer = getKafkaProducer();
                }
            }
        }
        return producer ;
    }

    private static KafkaProducer getKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", Jboot.configValue("jboot.kafka.servers"));//xxx服务器ip
        props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
        props.put("retries", 0);//retries = MAX 无限重试，直到你意识到出现了问题:)
        props.put("batch.size", 16384);//producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        //batch.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        props.put("linger.ms", 1);//延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        props.put("buffer.memory", 33554432);//producer可以用来缓存数据的内存大小。
        props.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer(props);
        return producer;
    }

}
