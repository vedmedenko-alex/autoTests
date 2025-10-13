package com.myproject.api.tests.imperative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteTest extends ImperativeBaseTest {

    @Test
    public void testDelete() {
        Response resp = client.delete("/delete");
        Assert.assertEquals(resp.getStatusCode(), 200);
        String url = resp.jsonPath().getString("url");
        Assert.assertTrue(url.contains("/delete"));
    }
}
