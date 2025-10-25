package com.solvd.demo.api.tests.declarative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DeclarativePutTest extends DeclarativeBaseTest {

    @Test
    public void testPut() {
        Map<String, String> body = new HashMap<>();
        body.put("a", "b");
        Response r = api.put(body);
        Assert.assertEquals(r.getStatusCode(), 200);
        Assert.assertEquals(r.jsonPath().getString("json.a"), "b");
    }
}
