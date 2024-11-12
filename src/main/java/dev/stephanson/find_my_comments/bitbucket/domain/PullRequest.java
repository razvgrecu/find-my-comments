package dev.stephanson.find_my_comments.bitbucket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public record PullRequest(
    @Id String id,
    String title,
    String description,
    String reason,
    Author author,
    @JsonProperty("comment_count") Integer commentCount,
    @JsonProperty("created_on") Instant createdOn,
    @JsonProperty("updated_on") Instant updatedOn
) {
}
