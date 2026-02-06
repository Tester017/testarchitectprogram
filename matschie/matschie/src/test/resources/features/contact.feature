
Feature: Contact API
  Scenario: Create a contact with valid data
    Given I have valid contact details
    When I create a new contact
    Then the response status should be 201
