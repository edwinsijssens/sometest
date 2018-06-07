package com.spr.packagegenerator.db;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class DBUtility {
    
    
    private static final String SERVER_ADDRESS = "sn-int-viv-01";
    
    private static final String USERNAME = "vivInt";
    
    private static final String PASSWORD = "viv123";
    
    private static final String DB = "viv";
    
    public static void saveToDatabase(final Map<String, String> dataMap,
            final String collectionName, final String DBName) throws UnknownHostException {
        
        final MongoClient mongoClient = getMongoClient();
        final DB db = mongoClient.getDB(DBName);
        
        final DBCollection collection = db.getCollection(collectionName);
        final BasicDBObject basicDbObject = new BasicDBObject(dataMap);
        collection.insert(basicDbObject);
        
        mongoClient.close();
        
    }
    
    private static MongoClient getMongoClient() throws UnknownHostException {
        
        final ServerAddress server = new ServerAddress(SERVER_ADDRESS, 27017);
        MongoCredential mongoCredential;
        mongoCredential = MongoCredential.createCredential(USERNAME, DB, PASSWORD.toCharArray());
        final MongoClient mongoClient = new MongoClient(server, Arrays.asList(mongoCredential));
        return mongoClient;
        
    }
    
}
