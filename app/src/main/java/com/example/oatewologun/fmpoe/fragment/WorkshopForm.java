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
import com.example.oatewologun.fmpoe.model.ElevatorModel;
import com.example.oatewologun.fmpoe.model.WorkshopModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class WorkshopForm extends Fragment {
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
    @BindView(R.id.intercom)
    MaterialRatingBar intercom;
    @BindView(R.id.visual)
    MaterialRatingBar visual;
    @BindView(R.id.audio)
    MaterialRatingBar audio;
    @BindView(R.id.functionality)
    MaterialRatingBar functionality;
    @BindView(R.id.quantity)
    MaterialRatingBar quantity;
    @BindView(R.id.printers)
    MaterialRatingBar printers;
    @BindView(R.id.overall)
    MaterialRatingBar overall;
    @BindView(R.id.level)
    MaterialRatingBar level;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;

    public static WorkshopForm newInstance() {
        WorkshopForm fragment = new WorkshopForm();
        Bundle args = new Bundle();
        //args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workshop_form, container, false);
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
                ||intercom.getRating()==0
                ||visual.getRating()==0
                ||audio.getRating()==0
                ||functionality.getRating()==0
                ||quantity.getRating()==0
                ||printers.getRating()==0
                ||overall.getRating()==0
                ||level.getRating()==0){
            submit = false;
        }

        if(!submit){
            Toast.makeText(getActivity(), "Please fill complete form!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (!isInternetOn(getActivity())) {
                Toast.makeText(getActivity(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                return;
            }
            // submit data
            FillPOEForm activity = (FillPOEForm) getActivity();
            WorkshopModel model = new WorkshopModel();
            model.setLevel(activity.level);
            model.setRoom_id(activity.roomID);
            model.setSockets_adequacy((int) adequacy.getRating());
            model.setElectrical_fittings((int) operation.getRating());
            model.setCharging_stations((int) provision.getRating());
            model.setNetwork_connectivity((int) network.getRating());
            model.setInternet_speed((int) speed.getRating());
            model.setConnection_availability((int) availability.getRating());
            model.setInternet_user_friendliness((int) users.getRating());
            model.setIntercom_systems((int) intercom.getRating());
            model.setVisual_display((int) visual.getRating());
            model.setAudio_contact((int) audio.getRating());
            model.setComputer_quality((int) functionality.getRating());
            model.setComputer_quantity((int) quantity.getRating());
            model.setQlty_qnty_printers((int) printers.getRating());
            model.setQlty_computer_pritner((int) overall.getRating());
            model.setCleanliness_level((int) level.getRating());
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("workshop-ratings");
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
