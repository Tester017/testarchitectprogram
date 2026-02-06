
package steps;

import api.ContactAPI;
import context.ResponseContext;
import pojos.CreateContactRequest;
import pojos.CreateResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

import com.github.javafaker.Faker;

public class ContactSteps {
    private ContactAPI contactAPI;
    private CreateContactRequest contactRequest;
    private final ResponseContext context;
    private CreateResponse createResponse;
    Faker faker = new Faker();

    public ContactSteps(ResponseContext context) { 
        contactAPI = new ContactAPI();
        this.context = context; 
    }

    @Given("I have valid contact details")
    public void iHaveValidContactDetails() {
        contactRequest = new CreateContactRequest();
        contactRequest.setSalutation("Mr.");
        contactRequest.setFirstName(faker.name().firstName());
        contactRequest.setLastName(faker.name().lastName());
        contactRequest.setEmail("john.doe@example.com");
        contactRequest.setPhone("(555) 123-4567");
        contactRequest.setTitle("Software Engineer");
    }

    @When("I create a new contact")
    public void iCreateANewContact() {
        createResponse = contactAPI.createContact(contactRequest);
        Assert.assertNotNull(createResponse.getId(), "Contact id should not be null");
        context.setBaseResponse(createResponse);
    }

}
