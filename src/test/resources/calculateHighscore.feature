Feature: calculateHighscore

  As a logged-in User
  I Want to view the highscore list

  Scenario: view the highscore list
    Given I am logged in
    And I am on the hompepage
    When I click on element having id "highscoreList"
    Then I am on the higscore page
    And element having id "highscoreListTable" should be present
