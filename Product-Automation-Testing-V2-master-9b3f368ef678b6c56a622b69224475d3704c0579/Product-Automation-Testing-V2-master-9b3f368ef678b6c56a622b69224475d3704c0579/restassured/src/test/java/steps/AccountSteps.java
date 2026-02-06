
package steps;

import api.AccountAPI;
import context.ResponseContext;
import pojos.AccountRequest;
import pojos.CreateContactRequest;
import pojos.CreateResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class AccountSteps {
    private AccountAPI contactAPI;
    private AccountRequest	 accountRequest;

    private final ResponseContext context;
    private CreateResponse createResponse;
    Faker faker = new Faker();

    public AccountSteps(ResponseContext context) { 
        contactAPI = new AccountAPI();
        this.context = context; 
    }

    @Given("I have valid account id {string}")
    public void iHaveValidContactDetails(String accountId) {
    	accountRequest = new AccountRequest();
    	accountRequest.setint_start(0);
    	accountRequest.setaccount_id(Arrays.asList(accountId));
    }

    @When("I get existing account")
    public void iCreateANewContact() {

        createResponse = contactAPI.createAccount(accountRequest);
//        Assert.assertNotNull(createResponse.getId(), "Contact id should not be null");
        context.setBaseResponse(createResponse);
    	
    	
    	
    	
    }


}
