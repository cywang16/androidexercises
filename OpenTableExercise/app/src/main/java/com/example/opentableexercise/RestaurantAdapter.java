package com.example.opentableexercise;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;

import org.json.JSONException;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantAdapterViewHolder> {
    private static final String TAG = RestaurantAdapter.class.getSimpleName();
    private String[] mRestaurantData;

    public class RestaurantAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mRestaurantImage;
        public final TextView mRestaurantName;

        public RestaurantAdapterViewHolder(View view) {
            super(view);
            mRestaurantImage = (ImageView) view.findViewById(R.id.iv_item_image);
            mRestaurantName = (TextView) view.findViewById(R.id.tv_item_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public RestaurantAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.restaurant_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new RestaurantAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapterViewHolder holder, int position) {
        String restaurantJsonStr = mRestaurantData[position];
        try {
            String name = RestaurantUtils.getRestaurantJsonString(restaurantJsonStr, "name");
            Log.i(TAG, name);
            holder.mRestaurantName.setText(name);
            String image_url = RestaurantUtils.getRestaurantJsonString(restaurantJsonStr, "image_url");
            /*
            InputStream is = (InputStream) new URL(image_url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            holder.mRestaurantImage.setImageDrawable(d);
            */
            new GetImageTask(holder.mRestaurantImage).execute(image_url);
            Log.i(TAG, image_url);
        } catch (JSONException je) {
            Log.e(TAG, je.toString());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public int getItemCount() {
        if (null == mRestaurantData) return 0;
        else return mRestaurantData.length;
    }

    public void setRestaurantData(String[] restaurants) {
        mRestaurantData = restaurants;
        notifyDataSetChanged();
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
