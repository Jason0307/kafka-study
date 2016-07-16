package org.zhubao.spark.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

public class CouchbaseUtil {

	public static void main(String[] args) {
		Cluster cluster = CouchbaseCluster.create("localhost");
		Bucket bucket = cluster.openBucket("default");

        // Create a JSON Document
        JsonObject batchData = JsonObject.create()
            .put("timeUnit", "20160712")
            .put("events", EventBuilder.buildEventData());

        // Store the Document
        bucket.upsert(JsonDocument.create("BATCH_20160712_DAY", batchData));

        // Load the Document and print it
        // Prints Content and Metadata of the stored Document
        System.out.println(bucket.get("BATCH_20160712_DAY"));

        // Create a N1QL Primary Index (but ignore if it exists)
        bucket.bucketManager().createN1qlPrimaryIndex(true, false);

        // Perform a N1QL Query
        /*N1qlQueryResult result = bucket.query(
            N1qlQuery.simple("SELECT * FROM default WHERE timeUnit = '20160712' AND ANY event IN events SATISFIES event.eventId = '1234' END"));*/
        N1qlQueryResult result = bucket.query(
                N1qlQuery.simple("SELECT FIRST event FOR event IN events WHEN event.eventId = '12345' END FROM default WHERE timeUnit = '20160712'"));

        // Print each found Row
        for (N1qlQueryRow row : result) {
            // Prints {"name":"Arthur"}
            System.out.println(row);
        }
	}
}
