package com.solvd.demo.api.tests.imperative;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetTest extends ImperativeBaseTest {

    @Test
    public void testGet() {
        Response resp = client.get("/get");
        Assert.assertEquals(resp.getStatusCode(), 200, "GET /get should return 200");
        String url = resp.jsonPath().getString("url");
        Assert.assertTrue(url.contains("/get"), "Response url should contain /get");
    }
}
