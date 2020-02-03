package com.example.opentableexercise;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

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

    public static String[] getDiningStringsFromJson(String diningJsonStr, String query)
            throws JSONException, IOException {
        JSONObject diningJson = new JSONObject(diningJsonStr);
        JSONArray diningArray = diningJson.getJSONArray("restaurants");
        int count = diningJson.getInt("total_entries");
        String parsedDiningData[] = new String[count];
        int page = 1, i = 0;
        while (count > 0) {
            int j = i;
            while (j < i + 100 && j < parsedDiningData.length) {
                parsedDiningData[j] = diningArray.getJSONObject(j - i).toString();
                j++;
                count--;
            }
            i = j;
            if (i < parsedDiningData.length) {
                diningJson = new JSONObject(getDiningResponseFromQuery(String.format("%s&page=%d", query, ++page)));
                diningArray = diningJson.getJSONArray("restaurants");
            }
        }
        return parsedDiningData;
    }

    public static String getRestaurantJsonString(String restaurantJsonStr, String key)
            throws JSONException {
        JSONObject restaurantJson = new JSONObject(restaurantJsonStr);
        if (restaurantJson.has(key)) return restaurantJson.getString(key);
        else return "";
    }

    public static String getPreferredRestaurantLocation(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String keyForLocation = context.getString(R.string.pref_city_key);
        String defaultLocation = context.getString(R.string.pref_city_default);
        return prefs.getString(keyForLocation, defaultLocation);
    }
}
