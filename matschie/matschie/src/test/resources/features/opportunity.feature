
Feature: Opportunity API
  Scenario: Create an opportunity with valid data
    Given I have valid opportunity details
    When I create a new opportunity
    Then the response status should be 201
