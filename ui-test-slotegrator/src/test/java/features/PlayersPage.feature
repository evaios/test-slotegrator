Feature: Players page
  As a user,
  after clicking on Players,
  i want to be on Players page

  @ui_test
  @players
  Scenario: Open players page with players table

    Given I logged in with login "admin1" and password "[9k<k8^z!+$$GkuP" on HomePage
    When I click on Players in HomePage
    Then I should be at the players page
    And Information players table loaded
