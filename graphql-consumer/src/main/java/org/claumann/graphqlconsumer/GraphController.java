package org.claumann.graphqlconsumer;

import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query_me")
public class GraphController {

    private final HttpSyncGraphQlClient graphQlClient;

    public GraphController(HttpSyncGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    @GetMapping
    public ResponseEntity<PostResponse> queryWithString(@RequestParam final String id) {
        if (Math.random() < 0.5) {
            System.out.println("queryWith [ String ]");
            String document = "query fetchPost($id: ID!) {  postById(id: $id) {  id  content } } ";
            PostResponse project = graphQlClient
                    .document(document)
                    .variable("id", id)
                    .retrieveSync("postById")
                    .toEntity(PostResponse.class);

            return ResponseEntity.ok(project);
        }

        System.out.println("queryWith [ Document ]");
        PostResponse project = graphQlClient
                .documentName("postById")
                .variable("id", id)
                .retrieveSync("postById")
                .toEntity(PostResponse.class);

        return ResponseEntity.ok(project);
    }

}
