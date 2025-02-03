Feature: User Login

  Scenario: Successful login with valid credentials
    Given I send a request to login with username "mor_2314" and password "83r5^_"
    Then I should receive a valid authentication token

  Scenario: Unsuccessful login with invalid credentials
    Given I send a request to login with username "invalid_user" and password "wrong_password"
    Then I should receive an error message indicating invalid credentials
