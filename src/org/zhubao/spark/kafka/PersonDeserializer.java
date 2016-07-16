package org.zhubao.spark.kafka;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

public class PersonDeserializer implements Deserializer<Person> {

	private String encoding = "UTF8";

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		String propertyName = isKey ? "key.deserializer.encoding"
				: "value.deserializer.encoding";
		Object encodingValue = configs.get(propertyName);
		if (encodingValue == null)
			encodingValue = configs.get("deserializer.encoding");
		if (encodingValue != null && encodingValue instanceof String)
			encoding = (String) encodingValue;
	}

	@Override
	public Person deserialize(String topic, byte[] data) {
		System.out.println(data);
		ByteArrayInputStream bi = new ByteArrayInputStream(data);
		try {
			ObjectInputStream ois = new ObjectInputStream(bi);
			Person person = (Person) ois.readObject();
			System.out.println(person);
			return person;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void close() {

	}

}
