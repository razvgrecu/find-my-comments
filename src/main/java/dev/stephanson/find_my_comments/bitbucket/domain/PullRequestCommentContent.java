package dev.stephanson.find_my_comments.bitbucket.domain;

public record PullRequestCommentContent(
    String type,
    String raw,
    String markup,
    String html
) {
}
