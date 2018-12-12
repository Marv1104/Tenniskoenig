Feature: viewRules

  As a User
  I Want to read the rules of tenniskoenig

  Scenario: view the rules
    Given I am on the "hompepage"
    When I click on element having id "btnRegeln"
    Then I am on the "rulespage"
    And element having id "lblRules" should be present