package com.lv.couchbase;


import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class TestCouchbase {
	 public static void main(String[] args) throws Exception {
		 
		    // (Subset) of nodes in the cluster to establish a connection
		    List<URI> hosts = Arrays.asList(
		      new URI("http://127.0.0.1:8091/pools")
		    );
		 
		    // Name of the Bucket to connect to
		    String bucketdb = "default";
		 
		    // Password of the bucket (empty) string if none
		    String password = "lvbaolin123";
		 
		 // Create a cluster reference
		    CouchbaseCluster cluster = CouchbaseCluster.create("127.0.0.1");

		    // Connect to the bucket and open it
		    Bucket bucket = cluster.openBucket("default");

		    // Create a JSON document and store it with the ID "helloworld"
//		    JsonObject content = JsonObject.create().put("hello", "world");
//		    
//
//		    JsonObject user = JsonObject.empty()
//		    		  .put("firstname", "Walter")
//		    		  .put("lastname", "White")
//		    		  .put("job", "chemistry teacher")
//		    		  .put("age", 50);
//		    JsonDocument inserted = bucket.upsert(JsonDocument.create("user:1", user));
		    // Read the document and print the "hello" field
		    JsonDocument found = bucket.get("user:1");
		    bucket.counter("count:1", 1, 0);
		    System.out.println(bucket.counter("count:1", 0, 0).content());
		    System.out.println(found);
		    System.out.println("Couchbase is the best database in the " + found.content().getString("job"));

		    // Close all buckets and disconnect
		    cluster.disconnect();
		  }
}
