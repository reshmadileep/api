package stepDefintions;

import businessObjects.Post;
import requests.Post.RetrievePost;
import cucumber.api.java.en.*;
import requests.GetRequestBaseClass;
import requests.PostRequestBaseClass;
import requests.Post.CreatePost;

public class PostSteps {
    private World world;
    PostRequestBaseClass createPost;
    GetRequestBaseClass getPost;
    String condition;

    public PostSteps(World world) {
        this.world = world;
    }

    @When("I send a request to create a post with {string}")
    public void i_send_a_request_to_create_a_resource_with(String condition) {
    this. condition = condition;
        createPost = new CreatePost(this.world)
                .UpdateRequest(condition)
                .createRequest()
                .post();
    }

    @Then("a post is created and response is received")
    public void a_resource_is_created_and_response_is_received() {
        createPost.validateSuccessResponse(condition, Post.class);
    }

    @Then("a retrieve posts response is received")
    public void a_retrieve_resource_response_is_received() {
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
            getPost.isSingleItemReturned = true;
    }

    @Then("a 404 Not found response is received")
    public void aNotFoundResponseIsReceived() {
        getPost.validateErrorResponse(condition);
    }
}
