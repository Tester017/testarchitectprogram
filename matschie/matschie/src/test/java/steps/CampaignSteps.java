
package steps;

import api.CampaignAPI;
import context.ResponseContext;
import pojos.CreateResourceRequest;
import pojos.CreateResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CampaignSteps {
    private CampaignAPI campaignAPI;
    private CreateResourceRequest campaignRequest;
    private final ResponseContext context;
    private CreateResponse createResponse;
    private Faker faker = new Faker();

    public CampaignSteps(ResponseContext context) { 
        this.context = context; 
    }

    @Given("I have valid campaign details")
    public void iHaveValidCampaignDetails() {
        campaignAPI = new CampaignAPI();
        campaignRequest = new CreateResourceRequest();
        campaignRequest.setName("Test Campaign " + faker.random().hex(4));
        campaignRequest.setStatus("Planning");
        campaignRequest.setType("Traditional");
        campaignRequest.setStartDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        campaignRequest.setEndDate(LocalDate.now().plusDays(30).format(DateTimeFormatter.ISO_DATE));
        campaignRequest.setExpectedRevenue(100000.0);
        campaignRequest.setBudgetedCost(50000.0);
        campaignRequest.setDescription("Test Campaign Description");
    }

    @When("I create a new campaign")
    public void iCreateANewCampaign() {
        createResponse = campaignAPI.createCampaign(campaignRequest);
        Assert.assertNotNull(createResponse.getId(), "Campaign id should not be null");
        context.setBaseResponse(createResponse);
    }
}
