Feature: Payment Integration

  Scenario: Successful payment with valid card details
    Given I send a payment request with valid credit card details
    Then I should receive a successful payment response with status "Payment Successful"

  Scenario: Failed payment with invalid card details
    Given I send a payment request with invalid credit card details
    Then I should receive an error message stating "Invalid Card Details"

  Scenario: Refund an order
    Given I send a request to refund order with ID 10
    Then the order with ID 10 should be refunded successfully with status "Refunded"
