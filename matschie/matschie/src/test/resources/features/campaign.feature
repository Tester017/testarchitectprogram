
Feature: Campaign API
  Scenario: Create a campaign with valid data
    Given I have valid campaign details
    When I create a new campaign
    Then the response status should be 201
