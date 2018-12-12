Feature: viewPenalties

  As a logged-in User
  I Want to view my penalties

  Scenario: view the penalties list
    Given I am on the hompepage
    When I click on element having id "penalties"
    Then I am on the penaltie page
    And element having id "penaltyListTable" should be present