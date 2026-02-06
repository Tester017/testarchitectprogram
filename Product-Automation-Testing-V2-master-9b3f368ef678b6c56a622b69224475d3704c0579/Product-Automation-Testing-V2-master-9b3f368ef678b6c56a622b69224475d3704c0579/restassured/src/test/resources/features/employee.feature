Feature: Employee API

  Scenario: Create a resource with valid data
    Given I have valid employee details
    When I create a new employee
    Then the response status should be 200

