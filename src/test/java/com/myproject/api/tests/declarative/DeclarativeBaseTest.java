package com.myproject.api.tests.declarative;

import com.myproject.api.ApiClientFactory;
import com.myproject.api.IHttpBin;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.InputStream;
import java.util.Properties;

public class DeclarativeBaseTest {
    protected IHttpBin api;

    @BeforeClass
    public void setup() throws Exception {
        Properties props = new Properties();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("_config.properties")) {
            if (is != null) props.load(is);
        }
        String base = props.getProperty("base_url", "http://localhost");
        api = ApiClientFactory.create(IHttpBin.class, base);
    }
}
