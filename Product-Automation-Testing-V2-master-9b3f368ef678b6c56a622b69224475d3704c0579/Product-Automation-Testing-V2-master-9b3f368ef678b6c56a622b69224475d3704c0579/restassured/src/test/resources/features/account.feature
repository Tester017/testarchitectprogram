
Feature: Contact API
  Scenario: Create a contact with valid data
    Given I have valid account id "P_A_11333"
    When I get existing account
    Then the response status should be 200
