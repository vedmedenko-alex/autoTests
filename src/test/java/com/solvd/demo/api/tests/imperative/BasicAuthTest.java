package com.solvd.demo.api.tests.imperative;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class BasicAuthTest extends ImperativeBaseTest {

    @Test
    public void testBasicAuth() {
        String user = "user";
        String pass = "passwd";
        Response resp = client.getWithBasicAuth("/basic-auth/" + user + "/" + pass, user, pass);
        Assert.assertEquals(resp.getStatusCode(), 200);
        boolean authenticated = resp.jsonPath().getBoolean("authenticated");
        Assert.assertTrue(authenticated);
    }
}
