package com.example.oatewologun.fmpoe;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poe_form);
        ButterKnife.bind(this);

        level = getIntent().getStringExtra("level");
        roomID = getIntent().getStringExtra("room_id");
        form = getIntent().getStringExtra("form");

        title.setText(form);
        if(form.equals("Ambient Requirement")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new AmbientForm()).commit();
        }
        else if(form.equals("Spatial Requirement")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new SpatialForm()).commit();
        }
        else if(form.equals("Technology Requirement")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new TechnologyForm()).commit();
        }
        else if(form.equals("Building Support and Services")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new SupportForm()).commit();
        }
        else if(form.equals("Elevator")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new ElevatorForm()).commit();
        }
        else if(form.equals("Workshop/Lab")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new WorkshopForm()).commit();
        }
        else if(form.equals("Toilet")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new ToiletForm()).commit();
        }
    }
}
