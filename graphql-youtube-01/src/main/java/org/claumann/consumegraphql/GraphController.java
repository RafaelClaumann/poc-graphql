package org.claumann.consumegraphql;

import org.claumann.consumegraphql.dto.PostResponse;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/query_me")
public class GraphController {

    @PostMapping
    public ResponseEntity<?> test(@RequestParam final String id) {

        RestClient restClient = RestClient.create("http://localhost:8080/graphql");
        HttpSyncGraphQlClient graphQlClient = HttpSyncGraphQlClient.builder(restClient)
                .build();

        String document = """
                query fetchPost($id: ID!) {
                    postById(id: $id) {
                        id
                        content
                  }
                }
                """;

        PostResponse project = graphQlClient.document(document)
                .variable("id", id)
                .retrieveSync("postById")
                .toEntity(PostResponse.class);


        return ResponseEntity.ok(project);


    }

}
