/*
 *   Nathan Nasteff
 *   Delaware Tech
 *   Spring 2019
 */

package com.nate21588.location;
import android.app.ActivityOptions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.content.Intent;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Create instance of TempConverter

    // Define elements

    private EditText streetET;
    private EditText cityET;
    private EditText stateET;
    private EditText zipET;
    private Button submitBT;
    private Button clrBT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setExitTransition(new Explode());
        // Assign IDs to elements

        streetET = (EditText) findViewById(R.id.streetET);
        cityET = (EditText) findViewById(R.id.cityET);
        stateET = (EditText) findViewById(R.id.stateET);
        zipET = (EditText) findViewById(R.id.zipET);
        submitBT = (Button) findViewById(R.id.submitBT);
        clrBT= (Button) findViewById(R.id.clrBT);

        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);

        // Clicking button submits the location string

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myAddress = addressBuilder(streetET.getText().toString(), cityET.getText().toString(),
                        stateET.getText().toString(), zipET.getText().toString());
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                intent.putExtra("address", myAddress);
                startActivity(intent);
                finish();
            }

        });

        clrBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetET.setText("");
                cityET.setText("");
                stateET.setText("");
                zipET.setText("");
            }

        });


    }

    private String addressBuilder(String street, String city, String state, String zip) {
        String address = street + ", " + city + ", " + state + ", " + zip;
        return address;

    }
}