package com.epam.shop.server.json;

import com.google.gson.Gson;

public class JsonWriter {
    public String toJson(Object dto) {
        Gson gson = new Gson();
        return gson.toJson(dto);
    }
}
