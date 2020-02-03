package com.example.opentableexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.InputStream;
import java.net.URL;

public class DetailPage extends AppCompatActivity {
    private static final String TAG = DetailPage.class.getSimpleName();
    private String mRestaurant;
    private ImageView mImage;
    private TextView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        mImage = (ImageView) findViewById(R.id.iv_detail_image);
        mInfo = (TextView) findViewById(R.id.tv_info);
        Intent incomingIntent = getIntent();
        if (incomingIntent != null && incomingIntent.hasExtra(Intent.EXTRA_TEXT)) {
            mRestaurant = incomingIntent.getStringExtra(Intent.EXTRA_TEXT);
            try {
                String image_url = RestaurantUtils.getRestaurantJsonString(mRestaurant, "image_url");
                String name = RestaurantUtils.getRestaurantJsonString(mRestaurant, "name");
                String address = RestaurantUtils.getRestaurantJsonString(mRestaurant, "address");
                String city = RestaurantUtils.getRestaurantJsonString(mRestaurant, "city");
                String state = RestaurantUtils.getRestaurantJsonString(mRestaurant, "state");
                String postal_code = RestaurantUtils.getRestaurantJsonString(mRestaurant, "postal_code");
                String country = RestaurantUtils.getRestaurantJsonString(mRestaurant, "country");
                String phone = RestaurantUtils.getRestaurantJsonString(mRestaurant, "phone");
                new GetImageTask(mImage).execute(image_url);
                mInfo.setText(Html.fromHtml(String.format("<h2>%s</h2>%s<br>%s<br>%s<br>%s<br>%s<br><i>%s</i>",
                        name, address, city, state, postal_code, country, phone)));
            } catch (JSONException je) {
                Log.e(TAG, je.toString());
            }
        }
    }

    private class GetImageTask extends AsyncTask<String, Void, Drawable> {
        ImageView bmImage;

        public GetImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Drawable doInBackground(String... urls) {
            String urldisplay = urls[0];
            Drawable d = null;
            try {
                InputStream is = (InputStream) new URL(urldisplay).getContent();
                d = Drawable.createFromStream(is, "src name");
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return d;
        }

        protected void onPostExecute(Drawable result) {
            bmImage.setImageDrawable(result);
        }
    }
}
