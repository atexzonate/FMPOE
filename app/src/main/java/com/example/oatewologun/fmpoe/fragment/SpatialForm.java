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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class SpatialForm extends Fragment {
    @BindView(R.id.proximities)
    MaterialRatingBar proximities;
    @BindView(R.id.seating)
    MaterialRatingBar seating;
    @BindView(R.id.interior)
    MaterialRatingBar interior;
    @BindView(R.id.quality)
    MaterialRatingBar quality;
    @BindView(R.id.furniture)
    MaterialRatingBar furniture;
    @BindView(R.id.physical)
    MaterialRatingBar physical;
    @BindView(R.id.privacy)
    MaterialRatingBar privacy;
    @BindView(R.id.provision)
    MaterialRatingBar provision;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;

    public static SpatialForm newInstance() {
        SpatialForm fragment = new SpatialForm();
        Bundle args = new Bundle();
        //args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spatial_form, container, false);
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
        if(proximities.getRating()==0
                ||seating.getRating()==0
                ||interior.getRating()==0
                ||quality.getRating()==0
                ||furniture.getRating()==0
                ||physical.getRating()==0
                ||privacy.getRating()==0
                ||provision.getRating()==0){
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
            SpatialModel model = new SpatialModel();
            model.setLevel(activity.level);
            model.setRoom_id(activity.roomID);
            model.setProximities_space((int) proximities.getRating());
            model.setSeating_density((int) seating.getRating());
            model.setInterior_visibility((int) interior.getRating());
            model.setWritable_surface((int) quality.getRating());
            model.setFurniture_comfort((int) furniture.getRating());
            model.setStorage((int) physical.getRating());
            model.setPrivacy((int) privacy.getRating());
            model.setProvision((int) provision.getRating());
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("spatial-ratings");
            mDatabase.push().setValue(model);
            Toast.makeText(getActivity(), "Survey Submitted, Thank you!", Toast.LENGTH_SHORT).show();
            activity.form2 = true;
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
