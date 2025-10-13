package com.myproject.api.tests.imperative;

import com.myproject.api.BaseApiClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImperativeBaseTest {
    protected BaseApiClient client;
    protected Properties props = new Properties();

    @BeforeClass
    public void setup() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/ _config.properties")) {
            // fallback if resource path different
        }
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("_config.properties")) {
            if (is != null) {
                props.load(is);
            }
        }
        String base = props.getProperty("base_url", "http://localhost");
        client = new BaseApiClient(base);
    }
}
