Feature: Discount and Coupon Management

  Scenario: Apply a valid discount coupon
    Given I send a request to apply the coupon "SAVE20" during checkout
    Then the order total should be reduced by 20%

  Scenario: Apply an invalid coupon
    Given I send a request to apply the coupon "INVALID" during checkout
    Then I should receive an error message stating "Invalid Coupon"

  Scenario: Remove a coupon from the order
    Given I send a request to remove the applied coupon "SAVE20"
    Then the order total should revert to its original price
