package dev.stephanson.find_my_comments.bitbucket.domain;

public record PullRequestId(String id) {
    public static PullRequestId of(String id) {
        return new PullRequestId(id);
    }
    public static PullRequestId of(Integer id) {
        return new PullRequestId(id.toString());
    }

}
