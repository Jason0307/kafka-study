package org.zhubao.spark.couchbase;

import java.util.ArrayList;
import java.util.List;

import org.zhubao.spark.model.Event;

import com.alibaba.fastjson.JSON;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

public class EventBuilder {

	public static JsonArray buildEventData() {
		Event event1 = new Event("12345", "Jason Event", 123);
		Event event2 = new Event("13876", "Marco Event", 456);
		JsonObject jsonObject = JsonObject.fromJson(JSON.toJSONString(event1));
		JsonObject jsonObject2 = JsonObject.fromJson(JSON.toJSONString(event2));
		List<JsonObject> events = new ArrayList<JsonObject>();
		events.add(jsonObject);
		events.add(jsonObject2);
		return JsonArray.from(events);
	}
}
