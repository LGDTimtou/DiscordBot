package com.lgdtimtou;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

public class NoSQL {
    //region MongoDB uri
    private static final String uri = "mongodb+srv://LGDTimtou:Panis1234-@mcservers.zbnhk.mongodb.net/CouckeServer?retryWrites=true&w=majority";

    private static MongoClient client;
    private static MongoDatabase db;
    private static MongoCollection<Document> data;


    public static void connect(){
        client = MongoClients.create(uri);
        db = client.getDatabase("CouckeServer");
        data = db.getCollection("data");
    }

    public static MongoCollection<Document> getCollection(String name){
        return db.getCollection(name);
    }

    public static void setRecord(int record){
        Document query = new Document("_id", "record");
        Bson update = Updates.set("record", record);
        data.updateOne(query, update);
    }

    public static int getRecord(){
        Document query = data.find(Filters.eq("_id", "record")).first();
        return (int)query.get("record");
    }
}
