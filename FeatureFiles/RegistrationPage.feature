Feature: Successful Account Registration

  @regression @smoke
  Scenario: Registering new account
    Given user navigate to MyAccount page
    When user enters details into below fields
      | firstname | John                   |
      | lastname  | Kennedy                |
      | email     | john.kennedy@gmail.com |
      | password  | john                   |
    And click privacy policy
    And click continue button
    Then user should see the confirmation message "Your Account Has Been Created!"

  @sanity
  Scenario: Registering new account randomly
    Given user navigate to MyAccount page
    When user enters details (firstname: "", lastname: "", email: "", password:"")
    And click privacy policy
    And click continue button
    Then user should see the confirmation message "Your Account Has Been Created!"
