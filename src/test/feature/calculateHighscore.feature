Feature: calculateHighscore

  As a User
  I Want to view the highscore list

  Scenario: view the highscore list
    Given I am on the hompepage
    When I click on element having id "highscoreList"
    Then I am on the higscore page
    And element having id "highscoreListTable" should be present
