package dev.stephanson.find_my_comments.bitbucket.repository;

import dev.stephanson.find_my_comments.bitbucket.domain.PullRequestComment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PullRequestCommentRepository extends ReactiveCrudRepository<PullRequestComment, String> {
}
