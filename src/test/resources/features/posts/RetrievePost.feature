@retrievePosts

Feature: Retrieve Posts

    Scenario: 01. Retrieve all posts
        Given I am a user
        When I send a request to retrieve all the posts details
        Then a retrieve posts response is received

    Scenario: 02. Retrieve single post
        Given I am a user
        When I send a request to retrieve a post details with "valid details"
        Then a retrieve posts response is received

    Scenario: 03. Retrieve single post with invalid id
        Given I am a user
        When I send a request to retrieve a post details with "invalid details"
        Then a 404 Not found response is received