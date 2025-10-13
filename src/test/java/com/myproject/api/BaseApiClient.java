package com.myproject.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class BaseApiClient {
    private final String baseUrl;

    public BaseApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public Response get(String path) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .get(path)
                .andReturn();
    }

    public Response post(String path, Object body) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .body(body)
                .header("Content-Type", "application/json")
                .when()
                .post(path)
                .andReturn();
    }

    public Response put(String path, Object body) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .body(body)
                .header("Content-Type", "application/json")
                .when()
                .put(path)
                .andReturn();
    }

    public Response delete(String path) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .delete(path)
                .andReturn();
    }

    public Response getWithBasicAuth(String path, String user, String pass) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .auth().preemptive().basic(user, pass)
                .when()
                .get(path)
                .andReturn();
    }
}
