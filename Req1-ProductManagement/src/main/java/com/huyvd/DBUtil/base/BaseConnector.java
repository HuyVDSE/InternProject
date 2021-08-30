package com.huyvd.DBUtil.base;

import system.mongo.core.connector.MongoGatewayConnector;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.HashMap;

public class BaseConnector {
    private final static String DATABASE_NAME = "ProductManager";
    private HashMap<String, MongoObjectConnector> connectorMap = new HashMap<>();

    public MongoObjectConnector getMongoObjectConnector(String connectionName) {
        MongoObjectConnector mongoObjectConnector = connectorMap.get(connectionName);
        if (mongoObjectConnector == null) {
            mongoObjectConnector = MongoGatewayConnector.getMongoObjectConnector(DATABASE_NAME, connectionName);
            connectorMap.put(connectionName, mongoObjectConnector);
            System.out.println("Connect to " + connectionName + " success!");
        }
        return mongoObjectConnector;
    }
}
