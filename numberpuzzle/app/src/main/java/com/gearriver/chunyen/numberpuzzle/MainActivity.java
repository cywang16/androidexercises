package com.gearriver.chunyen.numberpuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button leftButton = null, rightButton = null;
    TextView pointsText = null;
    Random randy = null;
    int leftNum, rightNum, pointsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randy = new Random();
        leftButton = (Button) findViewById(R.id.left_button);
        rightButton = (Button) findViewById(R.id.right_button);
        pointsText = (TextView) findViewById(R.id.points_view);
        setNumbers();
        pointsNum = 0;
    }

    private void setNumbers()
    {
        if (null == randy) return;
        leftNum = randy.nextInt(10);
        rightNum = randy.nextInt(10);
        if (leftButton != null)
        {
            leftButton.setText(String.valueOf(leftNum));
        }

        if (rightButton != null)
        {
            rightButton.setText(String.valueOf(rightNum));
        }
    }

    private void UpdatePoints(int guessBig, int guessSmall)
    {
        if (guessBig >= guessSmall)
        {   // correct
            pointsNum++;
        }
        else
        {   // incorrect
            pointsNum--;
        }

        if (null != pointsText)
        {
            pointsText.setText(String.format("Points: %d", pointsNum));
        }
    }

    public void leftButtonClick(View view)
    {
        UpdatePoints(leftNum, rightNum);
        setNumbers();
    }

    public void rightButtonClick(View view)
    {
        UpdatePoints(rightNum, leftNum);
        setNumbers();
    }
}
