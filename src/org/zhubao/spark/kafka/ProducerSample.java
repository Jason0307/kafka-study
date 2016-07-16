package org.zhubao.spark.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerSample {

	public static void sendMessage(Person person) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "127.0.0.1:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(
				props);
			producer.send(new ProducerRecord<String, String>("tracking_person",person.toString()));

		producer.close();
	}

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("wyp", 23));
		persons.add(new Person("spark", 24));
		persons.add(new Person("kafka", 21));
		persons.add(new Person("iteblog", 22));
		for(Person p : persons) {
			sendMessage(p);
		}
	}

}