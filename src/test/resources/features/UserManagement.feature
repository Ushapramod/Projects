Feature: User Management

  Scenario: Get all users
    Given I send a request to fetch all users
    Then I should receive a list of users in the response

  Scenario: Get a single user
    Given I send a request to fetch user with ID 1
    Then I should receive the details of user with ID 1

  Scenario: Limit results when fetching users
    Given I send a request to fetch 5 users
    Then I should receive 5 users in the response

  Scenario: Sort users in descending order
    Given I send a request to fetch all users sorted by username in descending order
    Then I should receive users sorted by username in descending order

  Scenario: Add a new user
    Given I send a request to add a new user with email "john.doe@example.com" and username "john_doe" with a valid password
    Then the new user should be added to the system and return a new user ID

  Scenario: Update an existing user
    Given I send a request to update user with ID 7 with new details like email "john_new@example.com"
    Then the user with ID 7 should be updated with new email

  Scenario: Partially update an existing user
    Given I send a request to update user with ID 7 with only new phone number "1234567890"
    Then the user with ID 7 should be updated with the new phone number

  Scenario: Delete a user
    Given I send a request to delete user with ID 6
    Then the user with ID 6 should be deleted (response will indicate successful deletion)
