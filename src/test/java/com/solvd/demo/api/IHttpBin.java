package com.solvd.demo.api;

import io.restassured.response.Response;

public interface IHttpBin {
    Response get();
    Response post(Object body);
    Response put(Object body);
    Response delete();
    Response basicAuth(String user, String passwd);
}
