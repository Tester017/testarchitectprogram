package steps;

import java.util.Collections;

import api.EmployeeAPI;
import context.ResponseContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pojos.CreateEmployeeRequest;
import pojos.CreateResponse;

public class EmployeeSteps {
    private final EmployeeAPI employeeAPI;
    private final ResponseContext context;
    private CreateEmployeeRequest employeeRequest;
    private CreateResponse createResponse;

    public EmployeeSteps(ResponseContext context) {
        this.context = context;
        this.employeeAPI = new EmployeeAPI();
    }

    @Given("I have valid employee details")
    public void iHaveValidResourceDetails() {
        employeeRequest = new CreateEmployeeRequest();
        employeeRequest.setModified_from("");
        employeeRequest.setModified_to("");
        employeeRequest.setEmp_id(Collections.singletonList(""));
        employeeRequest.setInt_start(0);
        employeeRequest.setCount(10);
    }

    @When("I create a new employee")
    public void iCreateANewResource() {
        createResponse = employeeAPI.createResource(employeeRequest);
//        Assert.assertNotNull(createResponse, "Create response should not be null");
        context.setBaseResponse(createResponse);
    }

//    @Given("I have an existing resource as {string}")
//    public void iHaveExistingResource(String resourceId) {
//        context.setId(resourceId);
//    }
//
//    @When("I delete the resource")
//    public void iDeleteTheResource() {
//        BaseResponse deleteResponse = resourceAPI.deleteResource(context.getId());
//        context.setBaseResponse(deleteResponse);
//    }
}
