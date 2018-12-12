Feature: viewMatchHistory

  As a logged-in User
  I Want to view all match i have played

  Scenario: view the matchhistory list
    Given I am on the hompepage
    When I click on element having id "matchHistory"
    Then I am on the matchHistory page
    And element having id "matchHistoryListTable" should be present