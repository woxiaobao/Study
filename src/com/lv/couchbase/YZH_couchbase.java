package com.lv.couchbase;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;

public class YZH_couchbase {

	public static void main(String[] args) throws URISyntaxException {
		// TODO Auto-generated method stub
		 // (Subset) of nodes in the cluster to establish a connection
	    List<URI> hosts = Arrays.asList(
	      new URI("http://127.0.0.1:8091/pools")
	    );
	 
	    // Name of the Bucket to connect to
	    String bucketdb = "ejianlian";
	 
	    // Password of the bucket (empty) string if none
	    String password = "ejianlian";
	 
	 // Create a cluster reference
	    CouchbaseCluster cluster = CouchbaseCluster.create("192.168.1.2");

	    // Connect to the bucket and open it
	    Bucket bucket = cluster.openBucket(bucketdb,password);

	    // Create a JSON document and store it with the ID "helloworld"
//	    JsonObject content = JsonObject.create().put("hello", "world");
//	    
//
//	    JsonObject user = JsonObject.empty()
//	    		  .put("firstname", "Walter")
//	    		  .put("lastname", "White")
//	    		  .put("job", "chemistry teacher")
//	    		  .put("age", 50);
//	    JsonDocument inserted = bucket.upsert(JsonDocument.create("user:1", user));
	    // Read the document and print the "hello" field
	    JsonDocument doc = bucket.get("item:1");
	    System.out.println(doc.content().toMap());

	    // Close all buckets and disconnect
	    cluster.disconnect();
	}

}
