package com.sample.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by justin on 16/03/2017.
 * example taken from:
 * https://spring.io/guides/gs/async-method/
 * see also classes GitHubLookupService, GithubAsyncRunner
 *
 * TODO WORK IN PROGRESS
 * NOTE: remember to reenable asyc annotation in Application class
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BlogUser implements Serializable{

    private static final long serialVersionUID = 1L;


    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "BlogUser{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
