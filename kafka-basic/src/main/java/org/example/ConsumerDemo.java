package org.example;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

  private static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());

  public static void main(String[] args) {

    log.info("Kafka Consumer Demo");

    String groupId = "my-first-application";
    String topic = "demo_java";

    // create Producer Properties
    Properties properties = new Properties();

    // connect to the Kafka cluster localhost:9092
    properties.setProperty("bootstrap.servers", "localhost:9092");

    //create consumer configs
    properties.setProperty("key.deserializer", StringDeserializer.class.getName());
    properties.setProperty("value.deserializer", StringDeserializer.class.getName());
    properties.setProperty("group.id", groupId);
    properties.setProperty("auto.offset.reset", "earliest");

    // create the Producer
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

    // subscribe consumer to our topic(s)
    consumer.subscribe(Arrays.asList(topic));

    // poll for new data
    while (true) {
      log.info("Polling for new data");
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

      for (var record : records) {
        log.info("Key: " + record.key() + ", Value: " + record.value());
        log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
      }
    }
  }
}
