package com.lv.couchbase;


import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

public class TestCouchbase {
	 public static void main(String[] args) throws Exception {
		 
		    // (Subset) of nodes in the cluster to establish a connection
		    List<URI> hosts = Arrays.asList(
		      new URI("http://127.0.0.1:8091/pools")
		    );
		 
		    // Name of the Bucket to connect to
//		    String bucketdb = "default";
//		 
//		    // Password of the bucket (empty) string if none
//		    String password = "lvbaolin123";
//		 
//		 // Create a cluster reference
//		    CouchbaseCluster cluster = CouchbaseCluster.create("127.0.0.1");
//
//		    // Connect to the bucket and open it
//		    Bucket bucket = cluster.openBucket("default");
		    
		    
		 // Name of the Bucket to connect to
		    String bucketdb = "ejianlian";
		 
		    // Password of the bucket (empty) string if none
		    String password = "ejianlian";
		 
		 // Create a cluster reference
		    CouchbaseCluster cluster = CouchbaseCluster.create("192.168.1.2");

		    // Connect to the bucket and open it
		    Bucket bucket = cluster.openBucket(bucketdb,password);


		    // Create a JSON document and store it with the ID "helloworld"
//		    JsonObject content = JsonObject.create().put("hello", "world");
//		    
//
		    JsonObject demo = JsonObject.empty()
		    		  .put("firstname", "Walter")
		    		  .put("lastname", "White")
		    		  .put("job", "chemistry teacher")
		    		  .put("age", 50)
		    		  .put("type", "demo");
		    			
		    JsonDocument inserted = bucket.upsert(JsonDocument.create("demo:1", demo));
		    // Read the document and print the "hello" field
		    Thread.sleep(2000);
		    String SEARCH_COUNT_N1QL = "SELECT count(*) as count FROM ejianlian where type='demo'";
           
            N1qlQueryResult countQuery = bucket.query(N1qlQuery.simple(SEARCH_COUNT_N1QL));
            int total = (int) countQuery.rows().next().value().get("count");
            System.out.println(total);
//            int total = countQuery.rows().next().value().size();
//		    JsonDocument found = bucket.get("demo:1");
//		    System.out.println(found);
		    //bucket.counter("count:1", 1, 0);
//		    System.out.println(bucket.counter("count:1", 0, 0).content());
//		    System.out.println(countQuery.rows().next().value());
//		    System.out.println("Couchbase is the best database in the " + found.content().getString("job"));

		    // Close all buckets and disconnect
		    cluster.disconnect();
		  }
}
