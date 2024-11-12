package dev.stephanson.find_my_comments.bitbucket.client;

import dev.stephanson.find_my_comments.bitbucket.domain.BitBucketPage;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequest;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequestComment;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequestId;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequestState;
import dev.stephanson.find_my_comments.bitbucket.properties.BitbucketProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class BitbucketPullRequestClient {
    private final WebClient webClient;
    private final BitbucketProperties properties;
    private final ParameterizedTypeReference<BitBucketPage<PullRequest>> pullRequestType;
    private final ParameterizedTypeReference<BitBucketPage<PullRequestComment>> commentType;

    public BitbucketPullRequestClient(BitbucketProperties properties) {
        this.properties = properties;
        this.webClient = WebClient.builder().baseUrl(properties.baseUrl()).build();
        this.pullRequestType = new ParameterizedTypeReference<>() {};
        this.commentType = new ParameterizedTypeReference<>() {};
    }

    public Flux<PullRequest> listPullRequests(PullRequestState state, Pageable pageable) {
        return webClient.get()
            .uri(builder -> builder
                .path(properties.getRepositoryPullRequestPath())
                .queryParam("state", state.getExternalValue())
                .queryParam("page", pageable.getPageNumber() + 1)
                .queryParam("size", pageable.getPageSize())
                .build())
            .headers(this::setBasicAuth)
            .retrieve()
            .bodyToMono(pullRequestType)
            .flatMapIterable(BitBucketPage::values);
    }

    public Flux<PullRequestComment> listPullRequestComments(PullRequestId pullRequestId, Pageable pageable) {
        return webClient.get()
            .uri(builder -> builder
                .path(properties.getRepositoryPullRequestPath() + "/" + pullRequestId.id() + "/comments")
                .queryParam("page", pageable.getPageNumber() + 1)
                .queryParam("size", pageable.getPageSize())
                .build())
            .headers(this::setBasicAuth)
            .retrieve()
            .bodyToMono(commentType)
            .flatMapIterable(BitBucketPage::values);
    }

    private void setBasicAuth(HttpHeaders headers) {
        headers.setBasicAuth(properties.authentication().username(), properties.authentication().password());
    }
}
