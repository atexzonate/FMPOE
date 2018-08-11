package com.example.oatewologun.fmpoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class building_map extends AppCompatActivity {

    Button room1, room2, room3, room5, room6, room8, room9, room10, room12, room13, lift1, lift2, lift3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_map);

        room1 = findViewById(R.id.btnGq01);
        room2 = findViewById(R.id.btnGq02);
        room3 = findViewById(R.id.btnGq03);
        room5 = findViewById(R.id.btnGq05);
        room6 = findViewById(R.id.btnGq06);
        room8 = findViewById(R.id.btnGq08);
        room9 = findViewById(R.id.btnGq09);
        room10 = findViewById(R.id.btnGq10);
        room12 = findViewById(R.id.btnGq12);
        room13 = findViewById(R.id.btnGq13);
        lift1 = findViewById(R.id.btnLift1);
        lift2 = findViewById(R.id.btnLift2);
        lift3 = findViewById(R.id.btnLift3);

    }
}
