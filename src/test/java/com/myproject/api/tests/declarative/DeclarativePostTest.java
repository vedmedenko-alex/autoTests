package com.myproject.api.tests.declarative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.HashMap;

public class DeclarativePostTest extends DeclarativeBaseTest {

    @Test
    public void testPost() {
        Map<String,String> body = new HashMap<>();
        body.put("x","y");
        Response r = api.post(body);
        Assert.assertEquals(r.getStatusCode(), 200);
        Assert.assertEquals(r.jsonPath().getString("json.x"), "y");
    }
}
