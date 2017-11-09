package com.mongodb.m101j.crud;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;



public class DeleteTest {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
    	MongoDatabase database = client.getDatabase("course");
    	MongoCollection<Document> collection = database.getCollection("deleteTest");


    	collection.drop();
    	
    	// insert 10 docs with 2 random integers
    	for(int i=0; i < 8; i++) {
    		collection.insertOne(new Document("_id", i));
    	}
    	
    	collection.deleteMany(gt("_id", 4));
//    	collection.deleteOne(gt("_id", 4));

    	System.out.println("Finding with delete test: ");
    	List<Document> all = collection.find()
    			                       .into(new ArrayList<Document>());
    	for(Document cur: all) {
    		printJson(cur);
    	}
    	
    	
	}

}
