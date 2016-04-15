package com.lxdnz.nz.sunshine.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> mForecastAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Once the rootView is created for the Fragment, its time to
            // create some dummy data for the ListView.

            String [] data = {
                    "Mon 6/23 - Sunny - 31/17",
                    "Tue 6/24 - Foggy - 21/8",
                    "Wed 6/25 - Cloudy - 22/17",
                    "Thu 6/26 - Rainy - 18/11",
                    "Fri 6/28 - Foggy - 21/10",
                    "Sat 6/29 - TRAPPED IN WEATHERSTATION - 23/18",
                    "Sun 6/30 - Sunny - 30/7"
            };
            List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

            // Now we have some dummy data - create ArrayAdapter.
            //The ArrayAdapter will take some data source (like our dummy data) and
            // use it to populate the ListView.

            mForecastAdapter = new ArrayAdapter<String>(
                    //the current context
                    getActivity(),
                    //name of layout id
                    R.layout.list_item_forecast,
                    //id of textview to populate
                    R.id.list_item_forecast_textview,
                    //the data source
                    weekForecast);

            // Get a reference to the ListView, and attach the adapter to it.

            ListView listView = (ListView) rootView.findViewById(
                    R.id.listview_forecast);
            listView.setAdapter(mForecastAdapter);

            return rootView;
        }
    }
}