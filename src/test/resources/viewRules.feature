Feature: viewRules

  As a User
  I Want to read the rules of tenniskoenig

  Scenario: view the rules
    Given I am on the hompepage
    When I click on element having id "viewRules"
    Then I am on the rules page
    And element having id "rules" should be present