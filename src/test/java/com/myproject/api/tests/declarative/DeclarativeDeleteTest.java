package com.myproject.api.tests.declarative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeclarativeDeleteTest extends DeclarativeBaseTest {

    @Test
    public void testDelete() {
        Response r = api.delete();
        Assert.assertEquals(r.getStatusCode(), 200);
    }
}
