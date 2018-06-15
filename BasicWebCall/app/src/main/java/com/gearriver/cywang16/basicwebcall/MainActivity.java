package com.gearriver.cywang16.basicwebcall;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText tv_WebUrl = null;
    TextView tv_WebOutput = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_WebUrl = findViewById(R.id.tv_webUrl);
        tv_WebOutput = findViewById(R.id.tv_webOutput);
    }

    public void callWebService(View view) {
        if (null == tv_WebUrl) return;
        if (null == tv_WebOutput) return;

        Intent invoke = new Intent(this, WebCallService.class);
        WebResult webResult = new WebResult(new Handler());
        invoke.putExtra("webResult", webResult);
        invoke.putExtra("apiUrl", tv_WebUrl.getText().toString());
        startService(invoke);
    }

    class WebResult extends ResultReceiver {

        public WebResult(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (null == resultData) return;
            tv_WebOutput.setText("");
            tv_WebOutput.append(resultData.getString("result"));
            super.onReceiveResult(resultCode, resultData);
        }
    }
}
