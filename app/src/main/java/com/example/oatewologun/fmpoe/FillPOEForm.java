package com.example.oatewologun.fmpoe;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.oatewologun.fmpoe.fragment.AmbientForm;
import com.example.oatewologun.fmpoe.fragment.ElevatorForm;
import com.example.oatewologun.fmpoe.fragment.SpatialForm;
import com.example.oatewologun.fmpoe.fragment.SupportForm;
import com.example.oatewologun.fmpoe.fragment.TechnologyForm;
import com.example.oatewologun.fmpoe.fragment.ToiletForm;
import com.example.oatewologun.fmpoe.fragment.WorkshopForm;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FillPOEForm extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.container)
    LinearLayout container;
    public String level, roomID, form;
    public boolean form1, form2, form3, form4;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poe_form);
        ButterKnife.bind(this);

        level = getIntent().getStringExtra("level");
        roomID = getIntent().getStringExtra("room_id");
        form = getIntent().getStringExtra("form");

        title.setText(form);
        if (form.equals("Ambient Requirement")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new AmbientForm()).commit();
        } else if (form.equals("Spatial Requirement")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new SpatialForm()).commit();
        } else if (form.equals("Technology Requirement")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new TechnologyForm()).commit();
        } else if (form.equals("Building Support and Services")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new SupportForm()).commit();
        } else if (form.equals("Elevator")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new ElevatorForm()).commit();
        } else if (form.equals("Workshop/Lab")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new WorkshopForm()).commit();
        } else if (form.equals("Toilet")) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new ToiletForm()).commit();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNextPopup() {
        if (form1 && form2 && form3 && form4) {
            // all forms filled: move to home
            finishAffinity();
            Intent intent = new Intent(FillPOEForm.this, MainActivity.class);
            startActivity(intent);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to fill next form?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (!form1) {
                            title.setText("Ambient Requirement");
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.container, new AmbientForm()).commit();
                        } else if (!form2) {
                            title.setText("Spatial Requirement");
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.container, new SpatialForm()).commit();
                        } else if (!form3) {
                            title.setText("Technology Requirement");
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.container, new TechnologyForm()).commit();
                        } else if (!form4) {
                            title.setText("Building Support and Services");
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.container, new SupportForm()).commit();
                        }
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.scrollTo(0, 0);
                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                        finishAffinity();
                        Intent intent = new Intent(FillPOEForm.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
