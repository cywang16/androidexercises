package com.gearriver.apps.scratchglossary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    public void findItemClicked(View view)
    {
    }

    public void addItemClicked(View view)
    {
    }
}
