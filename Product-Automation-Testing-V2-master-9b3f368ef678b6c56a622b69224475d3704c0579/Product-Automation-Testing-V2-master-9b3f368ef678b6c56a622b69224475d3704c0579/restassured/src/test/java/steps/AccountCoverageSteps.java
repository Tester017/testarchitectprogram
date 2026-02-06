package steps;

import api.AccountCoverageAPI;
import context.ResponseContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pojos.AccountCoverageRequest;
import pojos.BaseResponse;
import pojos.CreateResponse;
import org.testng.Assert;

public class AccountCoverageSteps {
    private final AccountCoverageAPI accountCoverageAPI;
    private final ResponseContext context;
    private AccountCoverageRequest accountCoverageRequest;
    private CreateResponse createResponse;

    public AccountCoverageSteps(ResponseContext context) {
        this.context = context;
        this.accountCoverageAPI = new AccountCoverageAPI();
    }

    @Given("I have valid resource details")
    public void iHaveValidResourceDetails() {
    	accountCoverageRequest = new AccountCoverageRequest();
    	accountCoverageRequest.setModified_from("");
    	accountCoverageRequest.setModified_to("");
    	accountCoverageRequest.setInt_start(0);
    	accountCoverageRequest.setCount(0);
    }

    @When("I create a new resource")
    public void iCreateANewResource() {
        createResponse = accountCoverageAPI.createResource(accountCoverageRequest);
//        Assert.assertNotNull(createResponse, "Create response should not be null");
        context.setBaseResponse(createResponse);
    }


}
