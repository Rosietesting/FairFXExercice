package com.reqres.test.automation.scenario;

import com.restApp.rest.Post;
import org.junit.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.fest.assertions.api.Assertions.assertThat;


public class PostTest extends BaseScenarios {



    @Inject
    ScenarioGenerator sg;


    /**
     * This test checks if a post can be created
     */
    @Test
    public void createPostTest(){

        // GIVEN that the post to be created is set
        Post actualPost = new Post();
        actualPost.setUserId(265);
        actualPost.setId(101);
        actualPost.setTitle("Ducks in the pound");
        actualPost.setBody("There are 7  ducks in the pound. One is dark");


        // AND the post is created and retrieved via the API
        Post expectedPost = sg.postTasksBuilder()
                        .post()
                        .withUserId(actualPost.getUserId())
                        .withTitle(actualPost.getTitle())
                        .withBody(actualPost.getBody())
                        .executePostPost();
        assertThat(expectedPost).isNotNull();

        // THEN the  expected post is the same as the   post retrieved via the API
        assertThat(expectedPost).isEqualsToByComparingFields(actualPost);


    }

    /**
     * This test checks for a post that does not exist in the database, hence fails
     */
    @Test
    public void searchForAnInexistentPostTest(){

        // GIVEN that a post retrieved
        Post retrievedPost = sg.postTasksBuilder()
                .post()
                .withUserId(1)
                .withId(599999)
                .executeRetrievePost();
        if (retrievedPost == null) {

            fail(String.format("Post not found "));
        }
    }

    /**
     * This test updates an existing post
     */
    @Test
    public void updatePostTest(){

        // GIVEN that the post to be updated is retrieved
        Post retrievedPost = sg.postTasksBuilder()
                .post()
                .withUserId(1)
                .withId(5)
                .executeRetrievePost();

        if (retrievedPost != null) {


            assertThat(retrievedPost).isNotNull();
            // AND the title  and body of the post is updated
            Post actualPost = sg.postTasksBuilder()
                    .post()
                    .withUserId(retrievedPost.getUserId())
                    .withId(retrievedPost.getId())
                    .withTitle("New title for post 5")
                    .withBody("New boy for post 5")
                    .executeUpdatePost();


            // WHEN  the updated post is retrieved  via the API
            Post expectedPost = sg.postTasksBuilder()
                    .post()
                    .withUserId(1)
                    .withId(1)
                    .withTitle("New title for post 5")
                    .withBody("New boy for post 5")
                    .executeRetrievePost();

            //THEN expected post is equal to actual post
            assertThat(expectedPost)
                    .isLenientEqualsToByIgnoringFields(actualPost, "title", "body");
        }else{

            fail(String.format("Post not found "));
        }
    }



}
