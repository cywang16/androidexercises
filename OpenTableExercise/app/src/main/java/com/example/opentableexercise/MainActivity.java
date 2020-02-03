package com.example.opentableexercise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String[]> {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static boolean PREFERENCES_HAVE_BEEN_UPDATED = false;

    private RecyclerView mRecyclerView;
    private RestaurantAdapter mRestaurantAdapter;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_restaurants);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        int recyclerViewOrientation = RecyclerView.VERTICAL;
        boolean shouldReverseLayout = false;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRestaurantAdapter = new RestaurantAdapter();
        mRecyclerView.setAdapter(mRestaurantAdapter);
        LoaderManager.LoaderCallbacks<String[]> callback = MainActivity.this;
        Bundle bundleForLoader = null;
        LoaderManager.getInstance(this).initLoader(0, bundleForLoader, callback);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PREFERENCES_HAVE_BEEN_UPDATED) {
            Log.d(TAG, "onStart: preferences were updated");
            LoaderManager.getInstance(this).restartLoader(0, null, this);
            PREFERENCES_HAVE_BEEN_UPDATED = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<String[]>(this) {
            String mRestaurantData[] = null;

            @Override
            protected void onStartLoading() {
                if (mRestaurantData != null) {
                    deliverResult(mRestaurantData);
                } else {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String[] loadInBackground() {
                String diningQuery = "city=bellevue";

                try {
                    String diningJsonString = RestaurantUtils.getDiningResponseFromQuery(diningQuery);
                    String[] restaurantJsonData = RestaurantUtils.getDiningStringsFromJson(diningJsonString);

                    return restaurantJsonData;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(String[] data) {
                mRestaurantData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mRestaurantAdapter.setRestaurantData(data);
        if (null == data) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mErrorMessageDisplay.setVisibility(View.VISIBLE);
        } else {
            mErrorMessageDisplay.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {

    }

    private void invalidateData() {
        mRestaurantAdapter.setRestaurantData(null);
    }
}
