package com.example.oatewologun.fmpoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class Main2Activity extends AppCompatActivity {

    private Spinner mySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        CardView redCenter = findViewById(R.id.redCenter);
        mySpinner = findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new ItemSelectedListener());

    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(mySpinner.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(mySpinner.getSelectedItem()))) {
                // ToDo when first item is selected


            } else if (pos == 1){
                openMap();

            } else {
                Toast.makeText(parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

    public void openMap(){
        Intent intent = new Intent(this, building_map.class);
        startActivity(intent);
    }
}
