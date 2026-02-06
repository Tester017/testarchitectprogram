package steps;

import api.ContactAPI;
import context.ResponseContext;
import pojos.CreateContactRequest;
import pojos.CreateResponse;
import pojos.BaseResponse;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.util.Collections;

public class ContactSteps {
    private final ContactAPI contactAPI;
    private final ResponseContext context;
    private CreateContactRequest contactRequest;
    private CreateResponse createResponse;
    private final Faker faker = new Faker();

    public ContactSteps(ResponseContext context) {
        this.context = context;
        this.contactAPI = new ContactAPI();
    }

    @Given("I have valid contact details")
    public void iHaveValidContactDetails() {
        contactRequest = new CreateContactRequest();
        contactRequest.setCount(10);
//        contactRequest.setContact_id(Collections.singletonList(""));
        contactRequest.setModified_from("");
        contactRequest.setModified_to("");
    }

    @When("I create a new contact")
    public void iCreateANewContact() {
        createResponse = contactAPI.createResource(contactRequest);
//        Assert.assertNotNull(createResponse.getId(), "Contact id should not be null");
        context.setBaseResponse(createResponse);
    }

//    @Given("I have an existing contact as {string}")
//    public void iHaveAnExistingContact(String resourceId) {
//        context.setId(resourceId);
//    }
//
//    @When("I delete the contact")
//    public void iDeleteTheContact() {
//        BaseResponse deleteResponse = contactAPI.deleteResource(context.getId());
//        context.setBaseResponse(deleteResponse);
//    }
}
