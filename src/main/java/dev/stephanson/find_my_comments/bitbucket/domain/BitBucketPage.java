package dev.stephanson.find_my_comments.bitbucket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public record BitBucketPage<T>(
    List<T> values,
    @JsonProperty("pagelen") Integer total,
    Integer size,
    Integer page,
    Optional<String> next,
    Optional<String> previous
) {
}
