package dev.stephanson.find_my_comments.bitbucket.domain;

public enum PullRequestState {
    OPEN("OPEN"),
    MERGED("MERGED"),
    DECLINED("DECLINED");

    private final String externalValue;

    PullRequestState(String externalValue) {
        this.externalValue = externalValue;
    }

    public String getExternalValue() {
        return externalValue;
    }
}
