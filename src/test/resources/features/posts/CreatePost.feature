@createPost
Feature: Create Post

    Scenario: 01. Create a post
        Given I am a user
        When I send a request to create a post with "mandatory information"
        Then a post is created and response is received