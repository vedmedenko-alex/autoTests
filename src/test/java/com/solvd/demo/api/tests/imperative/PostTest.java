package com.solvd.demo.api.tests.imperative;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostTest extends ImperativeBaseTest {

    @Test
    public void testPost() {
        Map<String, String> body = new HashMap<>();
        body.put("hello", "world");
        Response resp = client.post("/post", body);
        Assert.assertEquals(resp.getStatusCode(), 200);
        String data = resp.jsonPath().getString("json.hello");
        Assert.assertEquals(data, "world");
    }
}
