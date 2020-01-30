package com.gearriver.cywang16.basicwebcall;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebCallService extends IntentService {
    // Used to write to the system log from this class.
    static final String LOG_TAG = "WebCallService";
    static final String OpenTable_API_URL = "http://opentable.herokuapp.com/api/restaurants";
    static OkHttpClient client = null;

    public WebCallService()
    {
        super(WebCallService.class.getName());
        if (client == null) client = new OkHttpClient();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (null == intent) return;
        ResultReceiver resultReceiver = intent.getParcelableExtra("webResult");
        String requestString = intent.getStringExtra("apiUrl");
        String url = String.format("%s?%s", OpenTable_API_URL, requestString);

        Request request = new Request.Builder()
                .url(url)
                .build();

        String processedData = "error";

        try {
            Response response = client.newCall(request).execute();
            processedData = response.body().string();
        } catch (IOException ie) {
            processedData = ie.getMessage();
            Log.e(LOG_TAG, String.format("error %s", processedData));
        }

        Bundle resultBundle = new Bundle();
        resultBundle.putString("result", processedData);
        resultReceiver.send(0, resultBundle);
        Log.i(LOG_TAG, String.format("sent %s", processedData));
    }
}
