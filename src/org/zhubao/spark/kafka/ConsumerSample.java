package org.zhubao.spark.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerSample {

	public static void main(String[] args) throws Exception {

		 Properties props = new Properties();
	     props.put("bootstrap.servers", "127.0.0.1:9092");
	     props.put("group.id", "jason");
	     props.put("enable.auto.commit", "false");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("auto.offset.reset", "latest");
	     props.put("session.timeout.ms", "30000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.zhubao.spark.kafka.PersonDeserializer");
	     KafkaConsumer<String, Person> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList("tracking_person"));
	     while (true) {
	         ConsumerRecords<String, Person> records = consumer.poll(100);
	         for (ConsumerRecord<String, Person> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
	     }
	}
}