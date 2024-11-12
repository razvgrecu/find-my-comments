package dev.stephanson.find_my_comments.bitbucket.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("find-my-comments.bitbucket")
public record BitbucketProperties(
    String workspace,
    String repository,
    String baseUrl,
    BitbucketAuthenticationProperties authentication
) {
    public record BitbucketAuthenticationProperties(String username, String password) {}

    public String getRepositoryPullRequestPath() {
        return "/" + workspace + "/" + repository + "/pullrequests" ;
    }
}
