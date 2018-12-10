Feature: create Match

  As a User
  I want to enter a played match

  Scenario: open create match form
    Given I am on the homepage
    When I click on element having id "createMatch"
    Then I should be on the enterMatch page
    And element having id "player1" should be present
    And element having id "player2" should be present

  Scenario: enter one vs one match against another child
    Given I am logged in
    And I am on the enterMatch page
    When I select "player2 name" option by text from dropdown having id "player2"
    And I uncheck checkbox having id "teammatch"
    Then element having id "set1Team1" should be present
    And element having id "set1Team2" should be present
    And element having id "set2Team1" should be present
    And element having id "set2Team2" should be present
    And element having id "set3Team1" should be present
    And element having id "set3Team2" should be present

  Scenario: enter one vs one match against an adult
    Given I am on the enterMatch page
    When I select "Erwachsener" option by text from dropdown having id "player2"
    And I uncheck checkbox having id "teammatch"
    Then element having id "timePlayed" should be present

  Scenario: enter team match
    Given I am on the enterMatch page
    When I check checkbox having id "teammatch"
    Then element having id "Player3" should be present
    And element having id "Player4" should be present
    And element having id "set1Team1" should be present
    And element having id "set1Team2" should be present
    And element having id "set2Team1" should be present
    And element having id "set2Team2" should be present
    And element having id "set3Team1" should be present
    And element having id "set3Team2" should be present

  Scenario: succesfully submit entered match
    Given I am on the enterMatch page
    And I enterd valid data
    When I click on element having id "submit"
    Then I should see a succes message

  Scenario: failure on submit entered form
    Given I am on the enterMatch page
    And I entered invalid Data
    When I click on element having id "submit"
    Then I should see a failure message