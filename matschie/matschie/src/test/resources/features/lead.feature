
Feature: Lead API
  Scenario: Create a lead with valid data
    Given I have valid lead details
    When I create a new lead
    Then the response status should be 201

  Scenario: Delete the created lead
    Given I have an existing lead as '00QJ1000003kGHvMAM'
    When I delete the lead
    Then the response status should be 204
