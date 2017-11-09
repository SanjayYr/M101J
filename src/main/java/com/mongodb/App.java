package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

public class App 
{
    public static void main( String[] args )
    {
    	MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
//    	MongoClient client2 = new MongoClient(new ServerAddress("localhost", 27017));
    	MongoClient client = new MongoClient(new ServerAddress(), options);
    	
    	MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary());

//    	MongoCollection<Document> coll2 = db.getCollection("test");
    	MongoCollection<BsonDocument> coll = db.getCollection("test", BsonDocument.class);
    	
        System.out.println( "Hello World!" );
    }
}
