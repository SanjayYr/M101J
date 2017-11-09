package com.mongodb.m101j.crud;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

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



public class FindWithSortSkipLimitTest {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
    	MongoDatabase database = client.getDatabase("course");
    	MongoCollection<Document> collection = database.getCollection("findWithSortSkipLimitTest");


    	collection.drop();
    	
    	// insert 10 docs with 2 random integers
    	for(int i=0; i < 10; i++) {
    		for(int j=0; j < 10; j++) {
    			collection.insertOne(new Document()
			             				.append("i", i)
			             				.append("j", j));
    		}
    		
    	}
    	
    	
    	Bson projection = fields(include("i", "j"),
                							 excludeId());
    	
    	// 1 for ascending order and -1 for descending order
//    	Bson sort = new Document("i", 1).append("j", -1);
    	
    	Bson sort = Sorts.orderBy(Sorts.ascending("i"), Sorts.descending("j"));
//    	Bson sort = Sorts.descending("j", "i");
    	System.out.println("Finding with projection: ");
    	List<Document> all = collection.find()
    			                       .projection(projection)
    			                       .skip(20)
    			                       .limit(50)
    			                       .sort(sort)
    			                       .into(new ArrayList<Document>());
    	for(Document cur: all) {
    		printJson(cur);
    	}
    	
    	
	}

}
