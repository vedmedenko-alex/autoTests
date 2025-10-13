package com.myproject.api.tests.imperative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTest extends ImperativeBaseTest {

    @Test
    public void testBasicAuth() {
        String user = "user";
        String pass = "passwd";
        Response resp = client.getWithBasicAuth("/basic-auth/"+user+"/"+pass, user, pass);
        Assert.assertEquals(resp.getStatusCode(), 200);
        boolean authenticated = resp.jsonPath().getBoolean("authenticated");
        Assert.assertTrue(authenticated);
    }
}
