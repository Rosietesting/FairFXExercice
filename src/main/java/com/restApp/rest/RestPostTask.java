package com.restApp.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restApp.PostTask;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.fail;

public class RestPostTask implements PostTask {

    @Value("${api.server.postResource}")
    private String postResource;

    private final Logger LOGGER = LoggerFactory.getLogger(RestPostTask.class);
    private RestConfig restConfig;
    private Post post;
    private ObjectMapper objectMapper;

    public RestPostTask(RestConfig restConfig) {
        this.restConfig = restConfig;
        this.post = new Post();
        this.post.setId(null);
        this.post.setUserId(null);
        this.post.setTitle("");
        this.post.setBody("");
    }


    @Override
    public PostTask withTitle(String title) {
        this.post.setTitle(title);
        return this;
    }

    @Override
    public PostTask withBody(String body) {
        this.post.setBody(body);
        return this;
    }

    @Override
    public PostTask withId(Integer id) {
        this.post.setId(id);
        return this;
    }

    @Override
    public PostTask withUserId(Integer userId) {
        this.post.setUserId(userId);
        return this;
    }


    @Override
    public ArrayList<HashMap<String, String>> executeRemovePost() {
        return null;
    }


    @Override
    public Post executeUpdatePost() {
        Response response = given()
                .and().relaxedHTTPSValidation()
                .contentType(restConfig.getContentType())
                .body(this.post)
                .when()
                .put(restConfig.getBaseUrl() + "/posts/1")
                .then().extract().response();
        try {

            final ObjectMapper mapper = new ObjectMapper();
            final Post post = mapper.convertValue(response.path(""), Post.class);
            LOGGER.info("Post {} update with title {} and body {}", post.getUserId(), post.getTitle(), post.getBody());
            return post;


        } catch (Exception e) {

            throw new RuntimeException("Error creating a new post", e);
        }


    }

    @Override
    public Post executePostPost() {
        Response response = given()
                .and().relaxedHTTPSValidation()
                .contentType(restConfig.getContentType())
                .body(this.post)
                .when()
                .post(restConfig.getBaseUrl() + "/posts/")
                .then().extract().response();
        try {

            final ObjectMapper mapper = new ObjectMapper();
            final Post post = mapper.convertValue(response.path(""), Post.class);
            LOGGER.info("Post {} created with title {} and body {}", post.getUserId(), post.getTitle(), post.getBody());
            return post;


        } catch (Exception e) {

            throw new RuntimeException("Error creating a new post", e);
        }

    }


    @Override
    public Post executeRetrievePost() {
        Post post = new Post();
        Response response = given()
                .and().relaxedHTTPSValidation()
                .contentType(restConfig.getContentType())
                .when()
                .queryParam("id", this.post.getId())
                .queryParam("userId", this.post.getUserId())
                .get(restConfig.getBaseUrl() + "/posts/")

                .then().extract().response();
        try {

            final ObjectMapper mapper = new ObjectMapper();
            post = mapper.convertValue(response.path("[0]"), Post.class);
            LOGGER.info("Post {} retrieved with title {} and body {}", post.getUserId(), post.getTitle(), post.getBody());

        } catch (Exception e) {
            post = null;
        }
        return post;
    }
}








