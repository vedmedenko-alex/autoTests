package com.myproject.api.tests.imperative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PutTest extends ImperativeBaseTest {

    @Test
    public void testPut() {
        Map<String, String> body = new HashMap<>();
        body.put("k","v");
        Response resp = client.put("/put", body);
        Assert.assertEquals(resp.getStatusCode(), 200);
        String val = resp.jsonPath().getString("json.k");
        Assert.assertEquals(val, "v");
    }
}
