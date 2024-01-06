Feature: Login With Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given user navigate to Login Page
    When user enters credentials (email: "admin@gmail.com", password:"admin")
    And click on login button
    Then user navigates to MyAccount Page

  @regression
  Scenario: Login with InValid Credentials
    Given user navigate to Login Page
    When user enters credentials (email: "admin@gmail.com", password:"admin123")
    And click on login button
    Then user should be on the Login page and verify title page "Account Login"

  @smoke @sanity
  Scenario Outline: Login Data Driven Test with multiple data
    Given user navigate to Login Page
    When user enters credentials (email: "<email>", password:"<password>")
    And click on login button
    Then user navigates to MyAccount Page

    Examples: 
      | email              | password |
      | admin@gmail.com    | admin    |
      | admin123@gmail.com | admin123 |
