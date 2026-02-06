Feature: Contact API

  Scenario: Create a contact with valid data
    Given I have valid contact details
    When I create a new contact
    Then the response status should be 200

  #Scenario: Delete the created contact
    #Given I have an existing contact as '701J1000000HKytIAG'
    #When I delete the contact
    #Then the response status should be 204
