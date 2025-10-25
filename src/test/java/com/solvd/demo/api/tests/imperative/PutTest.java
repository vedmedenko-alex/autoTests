package com.solvd.demo.api.tests.imperative;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PutTest extends ImperativeBaseTest {

    @Test
    public void testPut() {
        Map<String, String> body = new HashMap<>();
        body.put("k", "v");
        Response resp = client.put("/put", body);
        Assert.assertEquals(resp.getStatusCode(), 200);
        String val = resp.jsonPath().getString("json.k");
        Assert.assertEquals(val, "v");
    }
}
