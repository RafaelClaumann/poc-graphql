package org.claumann.graphqlyoutube.dataprovider.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;

// @Configuration
public class MongoConnectionConfig {

    private static final String DB_NAME = "forum-db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "pass";
    private static final String DB_PORT = "27017";
    private static final String MONGO_URI_TEMPLATE = "mongodb://%s:%s@127.0.0.1:%s";
    private static final String CONNECTION_URI = String.format(MONGO_URI_TEMPLATE, DB_USERNAME, DB_PASSWORD, DB_PORT);

    // @Bean
    public MongoTemplate mongoTemplate() {
        final MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_URI))
                .build();

        return new MongoTemplate(MongoClients.create(settings), DB_NAME);
    }

}
