package imdb.serial.com.visitservice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class ContentActivity extends AppCompatActivity  {
    private android.support.v4.app.FragmentManager fragmentManager;

    private RepairServicesFragment fragment;
    public static final String TAG = "ContentActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.repair_services:
                    android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new RepairServicesFragment()).commit();
                    Toast.makeText(getApplicationContext(), "You clicked on the repair services", Toast.LENGTH_LONG).show();


                    break;

                case R.id.cleaning_services:
                    Toast.makeText(getApplicationContext(), "You clicked on the cleaning services", Toast.LENGTH_LONG).show();
                  //  return true;
                    break;
                case R.id.handyman:
                    Toast.makeText(getApplicationContext(), "You clicked on the handyman services", Toast.LENGTH_LONG).show();
                    //return true;
                    break;

                case R.id.interior:
                    Toast.makeText(getApplicationContext(), "You clicked on the interior services", Toast.LENGTH_LONG).show();
                    break;



            }
            return true;
        }

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            //set the transition
            Transition ts = new Explode();
            ts.setDuration(5000);
            getWindow().setEnterTransition(ts);
            getWindow().setExitTransition(ts);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        fragmentManager= getSupportFragmentManager();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
      //  navigation.inflateMenu(R.menu.menu_main);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        switch (id){
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"You Clicked on the setings icon",Toast.LENGTH_LONG).show();
                break;
                    //onNewIntent(Intent intent);


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //  String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search

            PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                    getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    // TODO: Get info about the selected place.
                    Log.i(TAG, "Place: " + place.getName());

                    Toast.makeText(getApplicationContext(), "Congrats you have selected" + place.getName() + "", Toast.LENGTH_SHORT)
                            .show();
                    //query.
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.i(TAG, "An error occurred: " + status);
                }
            });


        }
    }
}
