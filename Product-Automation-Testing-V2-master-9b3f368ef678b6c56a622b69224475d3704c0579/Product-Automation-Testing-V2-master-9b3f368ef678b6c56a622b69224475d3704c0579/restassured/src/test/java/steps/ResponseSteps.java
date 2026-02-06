package steps;

import org.testng.Assert;

import context.ResponseContext;
import io.cucumber.java.en.Then;

public class ResponseSteps {
	
	private final ResponseContext context;

    public ResponseSteps(ResponseContext context) {
        this.context = context;
    }
	
    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        Assert.assertEquals(context.getBaseResponse().getStatusCode(), statusCode, "Expected status code mismatch");
    }


}
