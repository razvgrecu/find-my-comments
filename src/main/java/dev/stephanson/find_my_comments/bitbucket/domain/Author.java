package dev.stephanson.find_my_comments.bitbucket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Author(@JsonProperty("display_name") String displayName) {
}
