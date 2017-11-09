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



public class UpdateTest {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
    	MongoDatabase database = client.getDatabase("course");
    	MongoCollection<Document> collection = database.getCollection("updateTest");


    	collection.drop();
    	
    	// insert 10 docs with 2 random integers
    	for(int i=0; i < 8; i++) {
    		collection.insertOne(new Document()
    				             .append("_id", i)
    				             .append("x", i)
    				             .append("y", true));
    	}
    	
//    	collection.replaceOne(eq("x", 5), new Document("x", 20).append("updated", true));
//    	collection.updateOne(eq("x", 5), new Document("$set",
//    														 new Document("x", 20)
//    														 .append("updated", true)));
//    	collection.updateOne(eq("x", 5), Updates.set("x", 25));
//    	collection.updateOne(eq("x", 5), Updates.combine(Updates.set("x", 25), Updates.set("updated", true)));

    	// Upserts
//    	collection.updateOne(eq("_id", 9), Updates.combine(Updates.set("x", 25), Updates.set("updated", true)),
//    			new UpdateOptions().upsert(true));
//
    	collection.updateMany(Filters.gte("x", 5), Updates.inc("x", 2));
    	
    	System.out.println("Finding with update test: ");
    	List<Document> all = collection.find()
    			                       .into(new ArrayList<Document>());
    	for(Document cur: all) {
    		printJson(cur);
    	}
    	
    	
	}

}
