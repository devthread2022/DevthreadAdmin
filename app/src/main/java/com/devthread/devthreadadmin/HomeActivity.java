package com.devthread.devthreadadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.devthread.devthreadadmin.UI.Dashboard;

public class HomeActivity extends AppCompatActivity {
    final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fragment fragment = new Dashboard();
        loadFragment(fragment);
    }
    @SuppressLint("StaticFieldLeak")
    private void loadFragment(final Fragment featureFragment) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                if (featureFragment!=null)
                {
                    fm.beginTransaction().replace(R.id.frame_container, featureFragment, "featureFragment").addToBackStack("featureFragment").commit();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();

    }
}