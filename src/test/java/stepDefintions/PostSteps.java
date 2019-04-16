package stepDefintions;

import businessObjects.Post;
import io.restassured.mapper.ObjectMapperType;
import requests.Post.RetrievePost;
import cucumber.api.java.en.*;
import requests.GetRequestBaseClass;
import requests.PostRequestBaseClass;
import requests.Post.CreatePost;
import utilities.Utils;

public class PostSteps {
    private World world;
    private PostRequestBaseClass createPost;
    private GetRequestBaseClass getPost;
    private String condition;

    public PostSteps(World world) {
        this.world = world;
    }

    @When("I send a request to create a post with {string}")
    public void iSendARequestToCreateAResourceWith(String condition) {
        this.condition = condition;
        createPost = new CreatePost(this.world)
                .updateRequest(condition)
                .createRequest()
                .post();
    }

    @Then("a post is created and response is received")
    public void aResourceIsCreatedAndResponseIsReceived() {
        createPost.validateSuccessResponse(condition, Post.class);
        Post actual = (Post) createPost.getBusinessObject();
        world.add2Context("createdId",actual.getId());
        iSendARequestToRetrieveAPostDetailsWith("verify post");
        Post expected = getPost.getResponse().body().as(Post.class, ObjectMapperType.GSON);
        Utils.assertThatObjectsAreEqual(expected,actual);
    }

    @Then("a retrieve posts response is received")
    public void aRetrieveResourceResponseIsReceived() {
       getPost.validateSuccessResponse(condition, Post.class);
    }

    @When("I send a request to retrieve all the posts details")
    public void iSendARequestToRetrievePostsDetails() {
        iSendARequestToRetrieveAPostDetailsWith("all posts");
    }

    @When("I send a request to retrieve a post details with {string}")
    public void iSendARequestToRetrieveAPostDetailsWith(String condition) {
        this.condition = condition;
        getPost = new RetrievePost(world)
                .updateRequest(condition)
                .createRequest()
                .get();
        if(!condition.equals("all posts"))
            getPost.setSingleItemReturned(true);
    }

    @Then("a 404 Not found response is received")
    public void aNotFoundResponseIsReceived() {
        getPost.validateErrorResponse(condition);
    }
}
