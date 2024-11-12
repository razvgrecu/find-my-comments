package dev.stephanson.find_my_comments.bitbucket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record PullRequest(
    @JsonProperty("comment_count") Integer commentCount,
    String id,
    String title,
    String description,
    Author author,
    String reason,
    @JsonProperty("created_on") Instant createdOn,
    @JsonProperty("updated_on") Instant updatedOn
) {
}
