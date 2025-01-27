Feature: User Authentication and Authorization

  Scenario: Login with valid credentials
    Given I send a login request with valid username "mor_2314" and password "83r5^_"
    Then I should receive a valid authentication token

  Scenario: Login with invalid credentials
    Given I send a login request with invalid username "invalid_user" and password "wrong_password"
    Then I should receive an error message stating "Invalid credentials"

  Scenario: Access protected endpoint with valid token
    Given I send a request to access a protected resource with a valid token
    Then I should receive a successful response with status 200

  Scenario: Access protected endpoint with invalid token
    Given I send a request to access a protected resource with an expired or invalid token
    Then I should receive an error message stating "Token expired" or "Unauthorized"

  Scenario: Logout user
    Given I send a request to log out a user with valid token
    Then I should receive a successful logout message
