package dev.stephanson.find_my_comments.bitbucket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record PullRequestComment(
    String id,
    String type,
    Boolean deleted,
    Boolean pending,
    PullRequestCommentContent content,
    Author user,
    @JsonProperty("pull_request") CommentPullRequest pullRequest,
    @JsonProperty("created_on") Instant createdOn,
    @JsonProperty("updated_on") Instant updatedOn
) {
}
