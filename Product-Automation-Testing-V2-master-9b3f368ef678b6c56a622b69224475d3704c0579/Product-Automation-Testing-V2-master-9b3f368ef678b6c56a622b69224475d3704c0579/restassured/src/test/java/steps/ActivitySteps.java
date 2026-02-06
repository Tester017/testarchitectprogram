package steps;

import api.ActivityAPI;
import context.ResponseContext;
import pojos.CreateActivityRequest;
import pojos.CreateResponse;
import pojos.BaseResponse;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.util.Collections;

public class ActivitySteps {
    private final ActivityAPI activityAPI;
    private final ResponseContext context;
    private CreateActivityRequest activityRequest;
    private CreateResponse createResponse;
    private final Faker faker = new Faker();

    public ActivitySteps(ResponseContext context) {
        this.context = context;
        this.activityAPI = new ActivityAPI();
    }

    @Given("I have valid activity details")
    public void iHaveValidActivityDetails() {
        activityRequest = new CreateActivityRequest();
        activityRequest.setCount(10);
//        activityRequest.setActivity_id(Collections.singletonList(""));
        activityRequest.setModified_from("");
        activityRequest.setModified_to("");
    }

    @When("I create a new activity")
    public void iCreateANewActivity() {
        createResponse = activityAPI.createResource(activityRequest);
//        Assert.assertNotNull(createResponse.getId(), "Activity id should not be null");
        context.setBaseResponse(createResponse);
    }

//    @Given("I have an existing activity as {string}")
//    public void iHaveAnExistingActivity(String resourceId) {
//        context.setId(resourceId);
//    }
//
//    @When("I delete the activity")
//    public void iDeleteTheActivity() {
//        BaseResponse deleteResponse = activityAPI.deleteResource(context.getId());
//        context.setBaseResponse(deleteResponse);
//    }
}
