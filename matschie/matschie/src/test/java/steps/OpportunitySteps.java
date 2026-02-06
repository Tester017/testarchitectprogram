
package steps;

import api.OpportunityAPI;
import context.ResponseContext;
import pojos.CreateOpportunityRequest;
import pojos.CreateResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import com.github.javafaker.Faker;

public class OpportunitySteps {
    private OpportunityAPI opportunityAPI;
    private CreateOpportunityRequest opportunityRequest;
    private final ResponseContext context;
    private CreateResponse createResponse;
    private Faker faker = new Faker();

    public OpportunitySteps(ResponseContext context) { 
        this.context = context; 
    }

    @Given("I have valid opportunity details")
    public void iHaveValidOpportunityDetails() {
        opportunityAPI = new OpportunityAPI();
        opportunityRequest = new CreateOpportunityRequest();
        opportunityRequest.setName(faker.company().name());
        opportunityRequest.setCloseDate(LocalDate.now().plusDays(2).format(DateTimeFormatter.ISO_DATE));
        opportunityRequest.setAmount(faker.number().randomDouble(2, 1000, 50000));
        opportunityRequest.setStageName("Prospecting");
    }

    @When("I create a new opportunity")
    public void iCreateANewOpportunity() {
        createResponse = opportunityAPI.createOpportunity(opportunityRequest);
        Assert.assertNotNull(createResponse.getId(), "Opportunity id should not be null");
        context.setBaseResponse(createResponse);
    }
}
