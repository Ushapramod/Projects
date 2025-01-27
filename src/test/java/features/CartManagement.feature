Feature: Cart Management
@Getallcarts
 Scenario: Get all carts
    Given I send a request to fetch all carts using endpoint "Getallcarts" and "GET" method
		Then I should receive a list of carts in the response

@Getasinglecart
  Scenario: Get a single cart
    Given I send a request to fetch cart with ID 5 using endpoint "GetASinglecart" and "GET" method
    Then I should receive the details of cart with ID 5

@Limitresultswhenfetchingcarts
  Scenario: Limit results when fetching carts
    Given I send a request to fetch 5 carts with the endpoint "LimitResults" and "GET" method
    Then I should receive 5 carts in the response
    
@Sortcartsindescendingorder
  Scenario: Sort carts in descending order
    Given I send a request to fetch all carts sorted by date in descending order with the endpoint "SortResults" and "GET" method
    Then I should receive carts sorted by date in descending order
    
@Getcartsinadaterange
  Scenario: Get carts in a date range
    Given I send a request to fetch carts from "2019-12-01" to "2020-10-10" with endpoint "GetCartsInDateRange" and "GET" method
    Then I should receive carts within that date range
    
@Getcartsbyuser
  Scenario: Get carts by user
    Given I send a request to fetch carts for user with ID 2 with the endpoint "Getusercarts" and "GET" method
    Then I should receive carts associated with user ID 2
    
@Addanewcart
  Scenario: Add a new cart for a user with products
  Given I send a request to add a new cart for user ID 5 with date "2020-02-03" and products "[{\"productId\": 5, \"quantity\": 1}, {\"productId\": 1, \"quantity\": 5}]" with endpoint "Addanewproduct" and "POST" method
  Then I should see the cart added successfully for user ID 5

@Updateaproduct
  Scenario: Update an existing cart
    Given I send a request to update cart with ID 7 with new products [{productId: 1, quantity: 3}] with "Updateaproduct" and "PUT" method
    Then the cart with ID 7 should be updated with new product details
    
@DeleteACart
  Scenario: Delete a cart
    Given I send a request to delete cart with ID 6 with "DeleteACart" and with "DELETE" method
    Then the cart with ID 6 should be deleted (response will indicate successful deletion)
