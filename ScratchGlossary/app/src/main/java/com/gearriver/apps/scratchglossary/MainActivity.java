package com.gearriver.apps.scratchglossary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    EditText et_Keyword = null, et_Info = null;
    TextView tv_Display = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Keyword = (EditText) findViewById(R.id.et_keyword);
        et_Info = (EditText) findViewById(R.id.et_info);
        tv_Display = (TextView) findViewById(R.id.tv_display);
    }

    public void findItemClicked(View view)
    {
    }

    public void addItemClicked(View view)
    {
        Intent intent = new Intent(this, AddItemAction.class);
        String keywordVal = et_Keyword.getText().toString().trim();
        String infoVal = et_Info.getText().toString().trim();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy, HH:mm, z");
        String datetimeVal = df.format(Calendar.getInstance().getTime());

        intent.putExtra(AddItemAction.keywordTag, keywordVal);
        intent.putExtra(AddItemAction.infoTag, infoVal);
        intent.putExtra(AddItemAction.datetimeTag, datetimeVal);
        startActivity(intent);
    }
}
