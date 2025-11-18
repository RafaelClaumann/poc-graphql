package org.claumann.graphqlconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query_me")
public class GraphController {

    private static final Logger logger = LoggerFactory.getLogger(GraphController.class);

    private final HttpSyncGraphQlClient graphQlClient;

    public GraphController(HttpSyncGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    @GetMapping
    public ResponseEntity<PostResponse> queryWithRandomDocumentSource(@RequestParam final String id) {
        if (Math.random() < 0.5) {
            String document = """
                    query fetchPost($id: ID!) {
                        postById(id: $id) {
                            id
                            subject
                            content
                            comments {
                                id
                                content
                                ... on ImageComment {
                                    imageUrl
                                }
                            }
                        }
                    }
                    """;
            PostResponse project = graphQlClient
                    .document(document)
                    .variable("id", id)
                    .retrieveSync("postById")
                    .toEntity(PostResponse.class);

            logger.info("Querying with [STRING] document");
            return ResponseEntity.ok(project);
        }

        PostResponse project = graphQlClient
                .documentName("postById")
                .variable("id", id)
                .retrieveSync("postById")
                .toEntity(PostResponse.class);

        logger.info("Querying with [FILE] document");
        return ResponseEntity.ok(project);
    }

}
