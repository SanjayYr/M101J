package com.mongodb.m101j.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HwQuestion1 {

public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase database = client.getDatabase("school");
    MongoCollection<Document> collection = database.getCollection("students");

    List<Document> all = collection.find().into(new ArrayList<Document>());
    int i = 0;
    Double s1 =0.0;
    Double s2 =0.0;
    Document doc1 = null;
    Document doc2 = null;
    for(Document cur:all) {
        List<Document> scores = (List<Document>) cur.get("scores");
        for(Document score:scores) {
            if(score.getString("type").equals("homework")) {

                if(i==0) {
                    i++;
                    s1 = (Double) score.get("score");
                    doc1 = score;

                }else {
                    i--;
                    s2 = (Double) score.get("score");
                    doc2 = score;
                    if(s1 < s2) {
//                        doc1.clear();
                        collection.updateOne(new Document("_id",cur.get("_id")), new Document("$pull", new Document("scores", new Document("score", s1))));
                    }else {
//                        doc2.clear();
                    	collection.updateOne(new Document("_id",cur.get("_id")), new Document("$pull", new Document("scores", new Document("score", s2))));
                    }
                }
            }


        }


    }

}
}