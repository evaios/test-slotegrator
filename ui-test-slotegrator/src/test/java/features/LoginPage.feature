Feature: Authorization
  As a user,
  I want to authorize and see administration panel

  @ui_test
  @login
  Scenario: Authorization with correct values

    Given Get the login page
    When I logged in with login "admin1" and password "[9k<k8^z!+$$GkuP"
    Then I should be authorized
    And See login "admin1" on home page loaded