package com.solvd.demo.api.tests.declarative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeclarativeBasicAuthTest extends DeclarativeBaseTest {

    @Test
    public void testBasic() {
        Response r = api.basicAuth("user", "passwd");
        Assert.assertEquals(r.getStatusCode(), 200);
        Assert.assertTrue(r.jsonPath().getBoolean("authenticated"));
    }
}
