package com.solvd.demo.api.tests.imperative;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeleteTest extends ImperativeBaseTest {

    @Test
    public void testDelete() {
        Response resp = client.delete("/delete");
        Assert.assertEquals(resp.getStatusCode(), 200);
        String url = resp.jsonPath().getString("url");
        Assert.assertTrue(url.contains("/delete"));
    }
}
