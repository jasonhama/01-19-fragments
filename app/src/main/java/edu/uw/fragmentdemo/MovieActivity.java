package edu.uw.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MovieActivity extends Activity implements MovieFragment.onMovieSelectionListener {

    private static final String TAG = "MovieActivity";

    private ArrayAdapter<Movie> adapter; //adapter for list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fragment transaction
        //represents a change in the fragment
        //need to get fragment manager
        FragmentManager manager = getFragmentManager();
        //make a new transaction
        FragmentTransaction ft = manager.beginTransaction();
        //add a fragment into activity_main's container
        ft.add(R.id.container, new MovieFragment());
        //gets transaction
        ft.commit();


    }

    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment detail = new DetailFragment();

        //create a bundle for information we want to pass to another fragment
        Bundle bundle = new Bundle();
        bundle.putString("title", movie.title);
        bundle.putString("imdb", movie.imdbId);

        //tie the bundle to the detail
        detail.setArguments(bundle);

        //swap fragments
        getFragmentManager().beginTransaction()
                .replace(R.id.container, detail)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() != 0){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
