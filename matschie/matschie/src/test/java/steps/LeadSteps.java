
package steps;

import api.LeadAPI;
import pojos.BaseResponse;
import pojos.CreateResponse;
import context.ResponseContext;
import pojos.LeadRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.github.javafaker.Faker;

public class LeadSteps {
    private LeadAPI leadAPI;
    private LeadRequest leadRequest;
    private final ResponseContext context;
    private CreateResponse createResponse;
    private Faker faker = new Faker();

    public LeadSteps(ResponseContext context) { 
        leadAPI = new LeadAPI();
        this.context = context; 
    } 
    
    @Given("I have valid lead details")
    public void iHaveValidLeadDetails() {
        leadRequest = new LeadRequest();
        leadRequest.setFirstName(faker.name().firstName());
        leadRequest.setLastName(faker.name().lastName());
        leadRequest.setCompany(faker.company().name());
        leadRequest.setEmail(faker.internet().emailAddress());
        leadRequest.setPhone(faker.phoneNumber().phoneNumber());
    }
    
    @When("I create a new lead")
    public void iCreateANewLead() {
        createResponse = leadAPI.createLead(leadRequest);
        Assert.assertNotNull(createResponse.getId(), "Lead id should not be null");
        context.setBaseResponse(createResponse);
    }
    
    @Given("I have an existing lead as {string}")
    public void iHaveExistingLead(String id) {
        context.setId(id);
    }
    
    @When("I delete the lead")
    public void iDeleteTheLead() {
        BaseResponse deleteResponse = leadAPI.deleteLead(context.getId());
        context.setBaseResponse(deleteResponse);
    }
}
