package dev.stephanson.find_my_comments.bitbucket.service;

import dev.stephanson.find_my_comments.bitbucket.client.BitbucketPullRequestClient;
import dev.stephanson.find_my_comments.bitbucket.domain.PullRequestId;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BitBucketService {
    private final BitbucketPullRequestClient client;

    public BitBucketService(BitbucketPullRequestClient client) {
        this.client = client;
    }

    @EventListener
    public void onStartup(ContextRefreshedEvent ignored) {
        var x = client.listPullRequestComments(
            PullRequestId.of(11598),
            Pageable.ofSize(10)
        ).blockFirst();

        System.out.println(x);
    }
}
