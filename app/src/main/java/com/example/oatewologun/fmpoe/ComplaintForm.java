package com.example.oatewologun.fmpoe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oatewologun.fmpoe.model.ComplaintModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintForm extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.submit)
    Button submit;
    private DatabaseReference mDatabase;
    private String level, roomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_form);
        ButterKnife.bind(this);

        level = getIntent().getStringExtra("level");
        roomID = getIntent().getStringExtra("room_id");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.submit)
    public void onViewClicked() {
        if(name.getText().toString().isEmpty()){
            name.setError("Required");
            return;
        }
        if(message.getText().toString().isEmpty()){
            message.setError("Required");
            return;
        }
        if(!isInternetOn(this)){
            Toast.makeText(this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        ComplaintModel model = new ComplaintModel();
        model.setLevel(level);
        model.setRoom_id(roomID);
        model.setUser_name(name.getText().toString());
        model.setComplaint(message.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference("complaints");
        mDatabase.push().setValue(model);
        Toast.makeText(this, "Complain Submitted, Thank you!", Toast.LENGTH_SHORT).show();
        finishAffinity();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static boolean isInternetOn(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()){
                return true;
            }
        }
        return false;
    }
}
