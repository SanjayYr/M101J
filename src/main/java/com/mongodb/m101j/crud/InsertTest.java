package com.mongodb.m101j.crud;

import java.awt.PrintJob;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import static com.mongodb.m101j.util.Helpers.printJson;

public class InsertTest {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
    	MongoDatabase db = client.getDatabase("course");
    	MongoCollection<Document> coll = db.getCollection("insertTest");


    	coll.drop();
    	
    	Document smith = new Document("name", "Smith")
    			                     .append("age", 30)
    			                     .append("profession", "programmer");
    	
    	Document jones = new Document("name", "Jones")
					                .append("age", 35)
					                .append("profession", "hacker");
    	
    	coll.insertMany(Arrays.asList(smith, jones));
    	printJson(smith);
    	printJson(jones);
	}

}
