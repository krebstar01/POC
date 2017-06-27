package com.sample.service.controller;

import com.sample.domain.model.BlogUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by justin on 16/03/2017.
 * * example taken from:
 * https://spring.io/guides/gs/async-method/
 * see also classes GithubAsyncRunner, BlogUser
 *
 * TODO WORK IN PROGRESS
 * NOTE: remember to reenable asyc annotation in Application class
 * AND Component Service (below)
 */
@Service
public class GitHubLookupService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);



    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Async("threadPoolTaskExecutor")
    public Future<BlogUser> findBlogUser(String blogUser) throws InterruptedException {
        logger.info("Looking up " + blogUser);
        String url = String.format("https://api.github.com/users/%s", blogUser);
        BlogUser results = restTemplate.getForObject(url, BlogUser.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return new AsyncResult<>(results);
    }



}
