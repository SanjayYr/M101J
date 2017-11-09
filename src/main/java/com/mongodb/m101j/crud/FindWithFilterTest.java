package com.mongodb.m101j.crud;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.mongodb.m101j.util.Helpers.printJson;

public class FindWithFilterTest {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
    	MongoDatabase database = client.getDatabase("course");
    	MongoCollection<Document> collection = database.getCollection("findTestWithFilter");


    	collection.drop();
    	
    	// insert 10 docs with 2 random integers
    	for(int i=0; i < 10; i++) {
    		collection.insertOne(new Document()
    				             .append("x", new Random().nextInt(2))
    				             .append("y", new Random().nextInt(100)));
    	}
    	
//    	Bson filter = new Document("x", 0).append("y", new Document("$gt", 10)
//    			                                       .append("$lt", 90));
    	
    	Bson filter = Filters.and(Filters.eq("x", 0), Filters.gt("y", 10), Filters.lt("y", 90));
    	
    	System.out.println("Finding with filter: ");
    	List<Document> all = collection.find(filter).into(new ArrayList<Document>());
    	for(Document cur: all) {
    		printJson(cur);
    	}
    	
    	
    	System.out.println("Count: ");
    	long count = collection.count(filter);
    	System.out.println(count);
    	
	}

}
