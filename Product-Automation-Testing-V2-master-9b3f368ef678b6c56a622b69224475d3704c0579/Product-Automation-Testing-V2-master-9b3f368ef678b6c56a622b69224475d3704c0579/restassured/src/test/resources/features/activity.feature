Feature: Activity API

  Scenario: Create an activity with valid data
    Given I have valid activity details
    When I create a new activity
    Then the response status should be 200

  #Scenario: Delete the created activity
    #Given I have an existing activity as '701J1000000HKytIAG'
    #When I delete the activity
    #Then the response status should be 204
