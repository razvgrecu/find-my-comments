package dev.stephanson.find_my_comments.bitbucket.service;

import dev.stephanson.find_my_comments.bitbucket.client.BitbucketPullRequestClient;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequest;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequestId;
import dev.stephanson.find_my_comments.bitbucket.events.TriggerCommentCollection;
import dev.stephanson.find_my_comments.bitbucket.events.TriggerPullRequestCollection;
import dev.stephanson.find_my_comments.bitbucket.repository.PullRequestRepository;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

import static dev.stephanson.find_my_comments.bitbucket.domain.PullRequestState.DECLINED;
import static dev.stephanson.find_my_comments.bitbucket.domain.PullRequestState.MERGED;
import static dev.stephanson.find_my_comments.bitbucket.domain.PullRequestState.OPEN;

@Service
public class BitBucketService {
    private final BitbucketPullRequestClient client;
    private final PullRequestRepository pullRequestRepository;
    private static final int DEFAULT_PAGE_SIZE = 50;
    private static final int DEFAULT_COMMENT_PAGE_SIZE = 150;
    private static final int DEFAULT_BITBUCKET_PAGE_SIZE = 25;
    private static final int MAX_PULL_REQUESTS = 12_000;

    public BitBucketService(BitbucketPullRequestClient client, PullRequestRepository pullRequestRepository) {
        this.client = client;
        this.pullRequestRepository = pullRequestRepository;
    }

    @EventListener
    public void collectPullRequests(TriggerPullRequestCollection ignored) {
        client
            .listPullRequests(OPEN, Pageable.ofSize(DEFAULT_BITBUCKET_PAGE_SIZE))
            .doOnNext(pullRequestRepository::save)
            .subscribe();

        Flux
            .fromStream(IntStream.range(0, 10).mapToObj(it -> PageRequest.of(it, DEFAULT_PAGE_SIZE)))
            .flatMap(it -> client.listPullRequests(DECLINED, it))
            .doOnNext(pullRequestRepository::save)
            .subscribe();

        Flux
            .fromStream(IntStream.range(0, 250).mapToObj(it -> PageRequest.of(it, DEFAULT_PAGE_SIZE)))
            .flatMap(it -> client.listPullRequests(MERGED, it))
            .doOnNext(pullRequestRepository::save)
            .subscribe();
    }

    @EventListener
    public void collectComments(TriggerCommentCollection ignored) {
            pullRequestRepository
                .findAll()
                .map(PullRequest::id)
                .map(PullRequestId::of)
                .flatMap(it -> client.listPullRequestComments(it, Pageable.ofSize(DEFAULT_COMMENT_PAGE_SIZE)))
                .subscribe();
    }
}
