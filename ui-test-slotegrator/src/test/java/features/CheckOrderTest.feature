Feature: Check ordering
  As a user,
  I want to order values after click on column in Players Page.

  Background: I should be on player page
    Given I logged in with login "admin1" and password "[9k<k8^z!+$$GkuP" on HomePage

  @ui_test
  @order
  Scenario: Check ordering by name

    Given Go to the players page
    And Information players table loaded
    When I select "100" in expand list
    And I add filter "sample" in field "email"
    And I collect data before sorting in column "External ID"
    Then I click on the column "External ID" for order
    And Check data in column "External ID" will be ordered