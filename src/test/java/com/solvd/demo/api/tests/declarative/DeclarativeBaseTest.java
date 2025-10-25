package com.solvd.demo.api.tests.declarative;

import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import com.solvd.demo.api.ApiClientFactory;
import com.solvd.demo.api.IHttpBin;

public class DeclarativeBaseTest {

    protected IHttpBin api;

    @BeforeClass
    public void setup() throws Exception {
        Properties props = new Properties();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("_config.properties")) {
            if (is != null) {
                props.load(is);
            }
        }
        String base = props.getProperty("base_url", "http://localhost");
        api = ApiClientFactory.create(IHttpBin.class, base);
    }
}
