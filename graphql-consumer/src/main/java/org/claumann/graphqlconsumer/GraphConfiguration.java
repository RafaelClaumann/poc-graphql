package org.claumann.graphqlconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;

@Configuration
public class GraphConfiguration {

    private static final String GRAPH_QL_API_URL = "http://localhost:8080/graphql";

    @Bean
    public HttpSyncGraphQlClient graphQlClient() {
        RestClient restClient = RestClient.create(GRAPH_QL_API_URL);
        return HttpSyncGraphQlClient.builder(restClient).build();
    }

}
