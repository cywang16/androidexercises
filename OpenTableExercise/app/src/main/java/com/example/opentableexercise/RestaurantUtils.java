package com.example.opentableexercise;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class RestaurantUtils {

    private static final String STATIC_OPENTABLE_URL =
            "https://opentable.herokuapp.com/api/restaurants";

    private static OkHttpClient client = null;

    public static String getDiningResponseFromQuery(String query) throws IOException {
        if (client == null) client = new OkHttpClient();
        String url = String.format("%s?%s&per_page=100", STATIC_OPENTABLE_URL, query);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String[] getDiningStringsFromJson(String diningJsonStr)
            throws JSONException {
        JSONObject diningJson = new JSONObject(diningJsonStr);
        JSONArray diningArray = diningJson.getJSONArray("restaurants");
        String parsedDiningData[] = new String[diningArray.length()];
        for (int i = 0; i < diningArray.length(); i++) {
            parsedDiningData[i] = diningArray.getJSONObject(i).toString();
        }
        return parsedDiningData;
    }

    public static String getRestaurantJsonString(String restaurantJsonStr, String key)
            throws JSONException {
        JSONObject restaurantJson = new JSONObject(restaurantJsonStr);
        return restaurantJson.getString(key);
    }
}
