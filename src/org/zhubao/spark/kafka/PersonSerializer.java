package org.zhubao.spark.kafka;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

public class PersonSerializer implements Serializer<Person> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public byte[] serialize(String topic, Person data) {
		byte[] bytes = null;
		if (null == data) {
			return null;
		} else {
			ByteArrayOutputStream bo = null;
			ObjectOutputStream oo = null;
			try {
				bo = new ByteArrayOutputStream();
				oo = new ObjectOutputStream(bo);
				oo.writeObject(data);
				bytes = bo.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bo.close();
					oo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	@Override
	public void close() {

	}

}
