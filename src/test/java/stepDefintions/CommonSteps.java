package stepDefintions;

import cucumber.api.java.en.Given;

public class CommonSteps {
    private World world;
    public CommonSteps(World world) {
        this.world = world;
    }

    @Given("I am a user")
    public void i_am_a_user() {
        // this is to make the scenario readable
    }
}