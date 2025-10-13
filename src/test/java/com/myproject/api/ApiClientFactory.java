package com.myproject.api;

import io.restassured.response.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ApiClientFactory {
    private final String baseUrl;

    private ApiClientFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static <T> T create(Class<T> apiInterface, String baseUrl) {
        ApiClientFactory factory = new ApiClientFactory(baseUrl);
        InvocationHandler handler = new ApiInvocationHandler(factory.baseUrl);
        Object proxy = Proxy.newProxyInstance(apiInterface.getClassLoader(),
                new Class[]{apiInterface}, handler);
        return apiInterface.cast(proxy);
    }

    private static class ApiInvocationHandler implements InvocationHandler {
        private final BaseApiClient client;

        public ApiInvocationHandler(String baseUrl) {
            this.client = new BaseApiClient(baseUrl);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String name = method.getName();
            switch (name) {
                case "get":
                    return client.get("/get");
                case "post":
                    return client.post("/post", args != null && args.length>0 ? args[0] : null);
                case "put":
                    return client.put("/put", args != null && args.length>0 ? args[0] : null);
                case "delete":
                    return client.delete("/delete");
                case "basicAuth":
                    String user = (String) args[0];
                    String pass = (String) args[1];
                    return client.getWithBasicAuth("/basic-auth/"+user+"/"+pass, user, pass);
                default:
                    throw new UnsupportedOperationException("Unknown method: " + name);
            }
        }
    }
}
