package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiContext {
    public RequestSpecification request;
    public Response response;
}
