package com.example.mvvm.network;

import java.util.HashMap;

public class RequestParams {

    private HashMap<String, String> params = new HashMap<>();

    public RequestParams put(String key, String value) {
        params.put(key, value);
        return this;
    }

    public int size() {
        return params.size();
    }

    public HashMap<String, String> getParams() {
        return params;
    }
}
