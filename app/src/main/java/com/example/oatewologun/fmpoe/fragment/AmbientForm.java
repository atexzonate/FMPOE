package com.example.oatewologun.fmpoe.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oatewologun.fmpoe.FillPOEForm;
import com.example.oatewologun.fmpoe.R;
import com.example.oatewologun.fmpoe.model.AmbientModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class AmbientForm extends Fragment {
    @BindView(R.id.five)
    RadioButton five;
    @BindView(R.id.four)
    RadioButton four;
    @BindView(R.id.three)
    RadioButton three;
    @BindView(R.id.two)
    RadioButton two;
    @BindView(R.id.one)
    RadioButton one;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.adequacy)
    MaterialRatingBar adequacy;
    @BindView(R.id.provision)
    MaterialRatingBar provision;
    @BindView(R.id.ease)
    MaterialRatingBar ease;
    @BindView(R.id.thermal)
    MaterialRatingBar thermal;
    @BindView(R.id.temperature)
    MaterialRatingBar temperature;
    @BindView(R.id.acoustic)
    MaterialRatingBar acoustic;
    @BindView(R.id.indoor)
    MaterialRatingBar indoor;
    @BindView(R.id.quality)
    MaterialRatingBar quality;
    @BindView(R.id.building)
    MaterialRatingBar building;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;
    private DatabaseReference mDatabase;

    public static AmbientForm newInstance() {
        AmbientForm fragment = new AmbientForm();
        Bundle args = new Bundle();
        //args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ambient_form, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //String type = getArguments().getString("type");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        boolean submit = true;
        if(group.getCheckedRadioButtonId() == -1
                ||adequacy.getRating()==0
                ||provision.getRating()==0
                ||ease.getRating()==0
                ||thermal.getRating()==0
                ||temperature.getRating()==0
                ||acoustic.getRating()==0
                ||indoor.getRating()==0
                ||quality.getRating()==0
                ||building.getRating()==0){
            submit = false;
        }

        if(!submit){
            Toast.makeText(getActivity(), "Please fill complete form!", Toast.LENGTH_SHORT).show();
        }
        else {
            if(!isInternetOn(getActivity())){
                Toast.makeText(getActivity(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                return;
            }
            // submit data
            FillPOEForm activity = (FillPOEForm) getActivity();
            AmbientModel model = new AmbientModel();
            model.setLevel(activity.level);
            model.setRoom_id(activity.roomID);
            if(group.getCheckedRadioButtonId() == R.id.five)
                model.setSatisfaction(5);
            else if(group.getCheckedRadioButtonId() == R.id.four)
                model.setSatisfaction(4);
            else if(group.getCheckedRadioButtonId() == R.id.three)
                model.setSatisfaction(3);
            else if(group.getCheckedRadioButtonId() == R.id.two)
                model.setSatisfaction(2);
            else if(group.getCheckedRadioButtonId() == R.id.one)
                model.setSatisfaction(1);
            model.setDaylighting_adequacy((int) adequacy.getRating());
            model.setProvision_exterior((int) provision.getRating());
            model.setLighting_control((int) ease.getRating());
            model.setThermal_comfort((int) thermal.getRating());
            model.setTemperature_control((int) temperature.getRating());
            model.setAcoustic_comfort((int) acoustic.getRating());
            model.setIndoor_air((int) indoor.getRating());
            model.setFinishes_colour((int) quality.getRating());
            model.setBuilding_design((int) building.getRating());
            mDatabase = FirebaseDatabase.getInstance().getReference("ambient-ratings");
            mDatabase.push().setValue(model);
            Toast.makeText(getActivity(), "Rating Submitted!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
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
