package com.gearriver.cywang16.basicwebcall;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

public class WebCallService extends IntentService {
    // Used to write to the system log from this class.
    static final String LOG_TAG = "WebCallService";

    public WebCallService()
    {
        super(WebCallService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (null == intent) return;
        ResultReceiver resultReceiver = intent.getParcelableExtra("webResult");
        String requestString = intent.getStringExtra("apiUrl");

        Bundle resultBundle = new Bundle();
        String processedData = String.format("processed %s", requestString);
        resultBundle.putString("result", processedData);
        resultReceiver.send(0, resultBundle);
        Log.i(LOG_TAG, String.format("sent %s", processedData));
    }
}
