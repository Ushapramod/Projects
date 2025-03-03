package stepDefinitions;

import io.cucumber.java.en.Given;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pojo.AddCart;
import pojo.UpdateCart;
import resources.APIResources;
import resources.TestDataBuild;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import pojo.AddCart;
import resources.APIResources;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static org.junit.Assert.fail;

import javax.management.RuntimeErrorException;

import resources.APIResources;

public class CartManagement extends Utils {
	static Response response;
	private String startDate;
	private String enddate;
	private static TestDataBuild testdatabuild;

	private Integer userId2;

	// Scenario: Get all carts
	@Given("^I send a request to fetch all carts using endpoint \"([^\"]*)\" and \"([^\"]*)\" method$")
	public void i_send_a_request_to_fetch_all_carts(String endpoint, String method) throws IOException {

		response = given().spec(Utils.getRequestSpecification()).when().get(APIResources.valueOf(endpoint).getResource());

	}

	@Then("^I should receive a list of carts in the response$")
	public void verifyResponse() {
		// Assertion to check the response
		response.then().assertThat().statusCode(200); // You can modify this depending on the expected status code
		response.then().assertThat().body("size()", greaterThan(0)); // Verify there's at least one cart
	}

	// Scenario: Get single cart
	@Given("I send a request to fetch cart with ID {int} using endpoint {string} and {string} method")
	public void i_send_a_request_to_fetch_cart_with_id(Integer cartId, String endpoint, String method) {
		// Getting the URL from APIResources enum and making the request
		response = given().when().get(APIResources.valueOf(endpoint).getResource());
	}

	@Then("I should receive the details of cart with ID {int}")
	public void i_should_receive_the_details_of_cart_with_id(Integer cartID) {
		// Assert the status code is 200
		response.then().assertThat().statusCode(400);

		// Get the cart details as a list of maps
		Map<String, Object> cartDetails = response.jsonPath().getMap("$");

		// Access the cart ID value from the map and assert
		Integer actualCartId = (Integer) cartDetails.get("id");

		// You can add a check here to verify the cartId matches
		assertEquals(cartID, actualCartId);
	}

	@Given("I send a request to fetch {int} carts with the endpoint {string} and {string} method")
	public void i_send_a_request_to_fetch_carts(Integer cartNum, String endpoint, String method) {
		response = given().when().get(APIResources.valueOf(endpoint).getResource());
	}

	@Then("I should receive {int} carts in the response")
	public void i_should_receive_carts_in_the_response(Integer cartSize) {
		List<Map<String, Object>> cartList = response.jsonPath().getList("$");
		Integer cartsSize = (Integer) cartList.size();
		assertEquals(cartsSize, cartSize);
	}

	@Given("I send a request to fetch all carts sorted by date in descending order with the endpoint {string} and {string} method")
	public void i_send_a_request_to_fetch_all_carts_sorted_by_date_in_descending_order_with_the_endpoint_and_method(
			String endpoint, String method) {
		response = given().when().get(APIResources.valueOf(endpoint).getResource());
	}

	@Then("I should receive carts sorted by date in descending order")
	public void i_should_receive_carts_sorted_by_date_in_descending_order() {
		List<Map<String, Object>> cartList = response.jsonPath().getList("$");

		// Extract dates from the cart list
		List<String> actualDates = cartList.stream().map(cart -> (String) cart.get("date"))
				.collect(Collectors.toList());

		// Create a copy of the dates list for sorting
		List<String> sortedDates = new ArrayList<>(actualDates);

		// Sort the copied list in descending order
		sortedDates.sort(Comparator.reverseOrder());

		// Assert that the actual dates are equal to the sorted dates
		assertEquals("Dates are not sorted in descending order!", sortedDates, actualDates);

	}

	// Scenario: Get carts in a date range
	// Given I send a request to fetch carts from "2019-12-01" to "2020-10-10" with
	// endpoint "GetCartsInDateRange" and "GET" method
	@Given("I send a request to fetch carts from {string} to {string} with endpoint {string} and {string} method")
	public void i_send_a_request_to_fetch_carts_from_to(String startDate, String endDate, String endpoint,
			String method) {
		this.startDate = startDate;
		this.enddate = endDate;
		response = given().when().get(APIResources.valueOf(endpoint).getResource());
	}

	@Then("I should receive carts within that date range")
	public void i_should_receive_carts_within_that_date_range() {
		List<Map<String, Object>> cartList = response.jsonPath().getList("$");
		List<String> dates = new ArrayList<>();

		for (Map<String, Object> cart : cartList) {
			String date1 = (String) cart.get("date");

			String date2 = date1.substring(0, 10);

			LocalDateTime cartDateandTime = LocalDateTime.parse(date2 + "T00:00:00");
			LocalDateTime startDate1 = LocalDateTime.parse(startDate + "T00:00:00");
			LocalDateTime endDate1 = LocalDateTime.parse(enddate + "T00:00:00");

			if (cartDateandTime.isAfter(startDate1) && cartDateandTime.isBefore(endDate1)) {

			} else {
				fail("Cart with date is out of range");
			}

		}

	}

	@Given("I send a request to fetch carts for user with ID {int} with the endpoint {string} and {string} method")
	public void i_send_a_request_to_fetch_carts_for_user_with_id(Integer userID, String endpoint, String method) {
		switch (method.toUpperCase()) {
		case "GET":
			response = given().when().get(APIResources.valueOf(endpoint).getResource());
			break;
		case "POST":
			response = given().when().post(APIResources.valueOf(endpoint).getResource());
			break;
		default:
			throw new IllegalArgumentException("Unsupported HTTP method: " + method);
		}
	}

	@Then("I should receive carts associated with user ID {int}")
	public void i_should_receive_carts_associated_with_user_id(Integer userID) {
		List<Map<String, Object>> map = response.jsonPath().getList("$");

		if (map == null || map.isEmpty()) {
			Assert.fail("The response does not contain any carts or is not in the expected format.");
		}

		for (Map<String, Object> values : map) {
			Integer userIdFromList = (Integer) values.get("userId");

			if (userIdFromList == null) {
				Assert.fail("Response contains an entry without a userId.");
			}

			System.out.println("User ID from list: " + userIdFromList); // For debugging purposes
			Assert.assertEquals(userIdFromList, userID);
			if (!userIdFromList.equals(userID)) {
				Assert.fail("The userId does not match the expected value. Expected: " + userID + ", but found: "
						+ userIdFromList);
			}

		}

	}

	@Given("I send a request to add a new cart for user ID {int} with date {string} and products {string} with endpoint {string} and {string} method")
	public void sendRequestToAddCart(Integer userId, String date, String productsJson, String endpoint, String method)
			throws IOException {
		testdatabuild = new TestDataBuild();

		// Check if method is POST as expected
		if (!method.equalsIgnoreCase("POST")) {
			throw new IllegalArgumentException("Invalid method type: " + method + ". Only POST method is allowed.");
		}

		// Build payload only if userId, date, and productsJson are not null
		if (userId == null || date == null || productsJson == null) {
			throw new IllegalArgumentException("Invalid input: userId, date, and productsJson cannot be null.");
		}

		AddCart addcart = testdatabuild.addCartPayload(userId, date, productsJson);

		// Perform the request
		if (addcart != null) {
			response = given().header("Content-Type", "application/json").body(addcart).when()
					.post(APIResources.valueOf("Addanewproduct").getResource());
		} else {
			throw new RuntimeException("Failed to build AddCart payload.");
		}

		// Check response status code
		if (response.statusCode() != 200) {
			throw new RuntimeException("Request failed with status code: " + response.statusCode());
		}
	}

	@Then("I should see the cart added successfully for user ID {int}")
	public void i_should_see_the_cart_added_successfully_for_user_id(Integer int1) {
		if (response == null) {
			throw new RuntimeException("Response is null. Ensure the request was successful.");
		}

		// Extract userId from response and check if it's valid
		int userId = response.jsonPath().getInt("userId");
		if (userId <= 0) {
			throw new RuntimeException("Invalid userId received in response: " + userId);
		}

		// Compare the response userId with the expected userId
		int userId1 = int1;
		Assert.assertEquals(userId, userId1);
	}

	@Given("I send a request to update cart with ID {int} with new products [\\{productId: {int}, quantity: {int}}] with {string} and {string} method")
	public void i_send_a_request_to_update_cart_with_id_with_new_products_quantity(Integer userID, Integer productID,
			Integer quantity, String endpoint, String method) throws IOException {
		testdatabuild = new TestDataBuild();

		UpdateCart updatecart = testdatabuild.UpdateCartPayLoad(userID, productID, quantity);
		response = given().spec(Utils.getRequestSpecification()).when()
				.put(APIResources.valueOf(endpoint).getResource());

	}

	@Then("the cart with ID {int} should be updated with new product details")
	public void the_cart_with_id_should_be_updated_with_new_product_details(Integer id) {
		Integer newId = (Integer) response.jsonPath().getInt("id");
		Assert.assertEquals(id, newId);
	}

	


	@Given("I send a request to delete cart with ID {int} with {string} and with {string} method")
	public void i_send_a_request_to_delete_cart_with_id(Integer userId, String endPoint, String method) throws IOException {
	    if (method.equalsIgnoreCase("DELETE")) {
	        response = given().spec(Utils.getRequestSpecification()).when().delete(APIResources.DeleteACart.getResource());
	    } else {
	        // You can add handling for other HTTP methods if needed
	        // For example, if method.equalsIgnoreCase("POST"), handle POST requests
	        throw new UnsupportedOperationException("Method " + method + " is not supported for this request.");
	    }
	}

	@Then("the cart with ID {int} should be deleted \\(response will indicate successful deletion)")
	public void the_cart_with_id_should_be_deleted_response_will_indicate_successful_deletion(Integer int1) throws IOException {
	    response.then().assertThat().statusCode(200);
	}}
