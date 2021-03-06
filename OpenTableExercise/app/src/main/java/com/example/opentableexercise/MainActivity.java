package com.example.opentableexercise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        RestaurantAdapter.RestaurantAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<String[]>,
        SharedPreferences.OnSharedPreferenceChangeListener {
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
        mRestaurantAdapter = new RestaurantAdapter(this);
        mRecyclerView.setAdapter(mRestaurantAdapter);
        LoaderManager.LoaderCallbacks<String[]> callback = MainActivity.this;
        Bundle bundleForLoader = null;
        LoaderManager.getInstance(this).initLoader(0, bundleForLoader, callback);
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
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

    @Override
    public void onClick(String restaurantJsonStr) {
        Intent intentToStartDetailActivity = new Intent(this, DetailPage.class);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, restaurantJsonStr);
        startActivity(intentToStartDetailActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent startSettingPage = new Intent(this, SettingPage.class);
            startActivity(startSettingPage);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        PREFERENCES_HAVE_BEEN_UPDATED = true;
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
                String diningQuery = String.format("city=%s", RestaurantUtils.getPreferredRestaurantLocation(MainActivity.this));

                try {
                    String diningJsonString = RestaurantUtils.getDiningResponseFromQuery(diningQuery);
                    String[] restaurantJsonData = RestaurantUtils.getDiningStringsFromJson(diningJsonString, diningQuery);

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
