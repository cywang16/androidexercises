package com.gearriver.apps.scratchglossary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddItemAction extends AppCompatActivity
{
    public static final String keywordTag = "keyword";
    public static final String infoTag = "info";
    public static final String datetimeTag = "datetime";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_action);

        Intent intent = getIntent();

        String keyword_val = intent.getStringExtra(keywordTag);
        EditText et_keyword_val = (EditText) findViewById(R.id.et_keyword_value);
        et_keyword_val.setText(keyword_val);

        String info_val = intent.getStringExtra(infoTag);
        EditText et_info_val = (EditText) findViewById(R.id.et_info_value);
        et_info_val.setText(info_val);

        String datetime_val = intent.getStringExtra(datetimeTag);
        TextView tv_datetime_val = (TextView) findViewById(R.id.tv_datetime_value);
        tv_datetime_val.setText(datetime_val);
    }
}
