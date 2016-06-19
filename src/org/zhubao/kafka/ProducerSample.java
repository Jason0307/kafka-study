package org.zhubao.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerSample {
	
	public static void sendMessage(String message) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.2.8:19092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSe"
				+ "rializer");

		Producer<String, String> producer = new KafkaProducer<String,String>(props);
		for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>("event",
					Integer.toString(i), message));

		producer.close();
	}

	public static void main(String[] args) {
//		sendMessage("123");
	}

}