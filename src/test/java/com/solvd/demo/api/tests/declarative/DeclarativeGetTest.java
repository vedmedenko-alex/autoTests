package com.solvd.demo.api.tests.declarative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeclarativeGetTest extends DeclarativeBaseTest {

    @Test
    public void testGet() {
        Response r = api.get();
        Assert.assertEquals(r.getStatusCode(), 200);
    }
}
