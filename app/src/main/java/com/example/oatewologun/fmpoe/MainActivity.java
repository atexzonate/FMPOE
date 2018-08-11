package com.example.oatewologun.fmpoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView fmPolicyCardView = findViewById(R.id.fmPolicy);
        CardView facultyBuildingCardView = findViewById(R.id.facultyBuilding);
        CardView emergencyInfoCardView = findViewById(R.id.emergenceInfo);
        CardView fmInfoCardView = findViewById(R.id.fmInfo);
        CardView otherFeaturesCardView = findViewById(R.id.otherFeatures);



        fmPolicyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "FM Policy not working yet", Toast.LENGTH_SHORT).show();
            }
        });


        facultyBuildingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFmPolicy();
            }
        });

        emergencyInfoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergenceyInfo();
            }
        });

        fmInfoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "FM Info not working yet", Toast.LENGTH_SHORT).show();
            }
        });

        otherFeaturesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Other Features not working yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openFmPolicy(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void openEmergenceyInfo(){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
