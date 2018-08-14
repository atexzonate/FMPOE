package com.example.oatewologun.fmpoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class FacultyBuilding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_building);

        CardView redCenter = findViewById(R.id.redCenter);
        redCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] options = getResources().getStringArray(R.array.redCenterDropDown);
                showDialog(options);
            }
        });
    }

    private void showDialog(final String [] options) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Level");
        builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(FacultyBuilding.this, FloorPlan.class);
                intent.putExtra("level", options[which]);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }
}
