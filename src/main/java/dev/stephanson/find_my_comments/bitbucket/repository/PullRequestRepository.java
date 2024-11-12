package dev.stephanson.find_my_comments.bitbucket.repository;

import dev.stephanson.find_my_comments.bitbucket.domain.PullRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PullRequestRepository extends ReactiveCrudRepository<PullRequest, String> {
}
