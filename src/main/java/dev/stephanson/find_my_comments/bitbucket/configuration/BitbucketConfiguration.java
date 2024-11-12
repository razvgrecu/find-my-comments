package dev.stephanson.find_my_comments.bitbucket.configuration;

import dev.stephanson.find_my_comments.bitbucket.client.BitbucketPullRequestClient;
import dev.stephanson.find_my_comments.bitbucket.properties.BitbucketProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BitbucketProperties.class)
public class BitbucketConfiguration {

    @Bean
    public BitbucketPullRequestClient bitbucketPullRequestClient(BitbucketProperties properties) {
        return new BitbucketPullRequestClient(properties);
    }

}
