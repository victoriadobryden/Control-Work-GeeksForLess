package com.mydomain.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mydomain.model.Manufacturer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
public class JsonDataStorage {
    private static final Gson gson = new Gson();

    public static void writeToJson(String filePath, Object data) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data,writer);
        }
    }

    public static <T> T readFromJson(String filePath, Type typeOfT) throws IOException {
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            file.createNewFile();
            return createEmptyResultForType(typeOfT);
        }

        try (Reader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, typeOfT);
        }
    }

    private static <T> T createEmptyResultForType(Type typeOfT) {
        if (typeOfT.equals(new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType())) {
            return (T) new HashMap<Integer, Manufacturer>();
        }

        throw new IllegalArgumentException("Type of T is not supported for empty result creation.");
    }
}
