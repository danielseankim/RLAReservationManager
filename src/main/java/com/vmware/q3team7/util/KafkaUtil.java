package com.vmware.q3team7.util;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author kdaniel
 *
 */
public class KafkaUtil {
    private static KafkaUtil instance;
    private static Producer<String, String> producer;
    // TODO it might be a better to pull consumer out into a different singleton class
    private static Consumer<String, String> consumer;

    private Producer<String, String> getProducer() {
        return this.producer;
    }

    private Consumer<String, String> getConsumer() {
        return this.consumer;
    }

    private static void initializeProducer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    private static void initializeConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
    }

    public static KafkaUtil getKafkaUtil() {
        if (instance == null) {
            initializeProducer();
            initializeConsumer();
        }
        return instance;
    }

    public void sendRecord(String topic, String key, String value) {
        ProducerRecord<String, String> record =  new ProducerRecord<String, String>(topic, key, value);
        getProducer().send(record);
    }

    public Consumer<String, String> getRecordConsumer() {
        return this.getConsumer();
    }
}
