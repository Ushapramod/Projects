Feature: Notifications and Alerts

  Scenario: Send order confirmation notification
    Given I send a request to place an order for user ID 3
    Then the user with ID 3 should receive an order confirmation notification

  Scenario: Send shipping update notification
    Given I send a request to update the shipping status for order ID 10 to "Shipped"
    Then the user should receive a notification about the shipping update

  Scenario: Send payment failure notification
    Given I send a request to process a payment for order ID 10 and the payment fails
    Then the user should receive a payment failure notification
