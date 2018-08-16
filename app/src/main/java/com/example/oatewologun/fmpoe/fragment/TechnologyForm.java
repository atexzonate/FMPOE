package com.example.oatewologun.fmpoe.fragment;


import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oatewologun.fmpoe.FillPOEForm;
import com.example.oatewologun.fmpoe.R;
import com.example.oatewologun.fmpoe.model.SpatialModel;
import com.example.oatewologun.fmpoe.model.TechnologyModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class TechnologyForm extends Fragment {
    @BindView(R.id.adequacy)
    MaterialRatingBar adequacy;
    @BindView(R.id.operation)
    MaterialRatingBar operation;
    @BindView(R.id.provision)
    MaterialRatingBar provision;
    @BindView(R.id.network)
    MaterialRatingBar network;
    @BindView(R.id.speed)
    MaterialRatingBar speed;
    @BindView(R.id.availability)
    MaterialRatingBar availability;
    @BindView(R.id.users)
    MaterialRatingBar users;
    @BindView(R.id.quality1)
    MaterialRatingBar quality1;
    @BindView(R.id.visual)
    MaterialRatingBar visual;
    @BindView(R.id.quality2)
    MaterialRatingBar quality2;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;

    public static TechnologyForm newInstance() {
        TechnologyForm fragment = new TechnologyForm();
        Bundle args = new Bundle();
        //args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.technology_form, container, false);
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
        if(adequacy.getRating()==0
                ||operation.getRating()==0
                ||provision.getRating()==0
                ||network.getRating()==0
                ||speed.getRating()==0
                ||availability.getRating()==0
                ||users.getRating()==0
                ||quality1.getRating()==0
                ||visual.getRating()==0
                ||quality2.getRating()==0){
            submit = false;
        }

        if(!submit){
            Toast.makeText(getActivity(), "Please fill complete form!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(!isInternetOn(getActivity())){
                Toast.makeText(getActivity(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                return;
            }
            // submit data
            FillPOEForm activity = (FillPOEForm) getActivity();
            TechnologyModel model = new TechnologyModel();
            model.setLevel(activity.level);
            model.setRoom_id(activity.roomID);
            model.setSockets_adequacy((int) adequacy.getRating());
            model.setElectrical_fittings((int) operation.getRating());
            model.setCharging_stations((int) provision.getRating());
            model.setNetwork_connectivity((int) network.getRating());
            model.setInternet_speed((int) speed.getRating());
            model.setConnection_availability((int) availability.getRating());
            model.setInternet_user_friendliness((int) users.getRating());
            model.setIntercom_systems((int) quality1.getRating());
            model.setVisual_display((int) visual.getRating());
            model.setAudio_contact((int) quality2.getRating());
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("technology-ratings");
            mDatabase.push().setValue(model);
            Toast.makeText(getActivity(), "Survey Submitted, Thank you!", Toast.LENGTH_SHORT).show();
            activity.form3 = true;
            activity.showNextPopup();
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
