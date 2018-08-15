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
import com.example.oatewologun.fmpoe.model.SupportModel;
import com.example.oatewologun.fmpoe.model.TechnologyModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class SupportForm extends Fragment {
    @BindView(R.id.cleanliness)
    MaterialRatingBar cleanliness;
    @BindView(R.id.maintenance)
    MaterialRatingBar maintenance;
    @BindView(R.id.fm)
    MaterialRatingBar fm;
    @BindView(R.id.security)
    MaterialRatingBar security;
    @BindView(R.id.entrance)
    MaterialRatingBar entrance;
    @BindView(R.id.clarity)
    MaterialRatingBar clarity;
    @BindView(R.id.quality)
    MaterialRatingBar quality;
    @BindView(R.id.wayfinding)
    MaterialRatingBar wayfinding;
    @BindView(R.id.conveniences)
    MaterialRatingBar conveniences;
    @BindView(R.id.water)
    MaterialRatingBar water;
    @BindView(R.id.fire)
    MaterialRatingBar fire;
    @BindView(R.id.regular)
    MaterialRatingBar regular;
    @BindView(R.id.health)
    MaterialRatingBar health;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;

    public static SupportForm newInstance() {
        SupportForm fragment = new SupportForm();
        Bundle args = new Bundle();
        //args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.support_form, container, false);
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
        if(cleanliness.getRating()==0
                ||maintenance.getRating()==0
                ||fm.getRating()==0
                ||security.getRating()==0
                ||entrance.getRating()==0
                ||clarity.getRating()==0
                ||quality.getRating()==0
                ||wayfinding.getRating()==0
                ||conveniences.getRating()==0
                ||water.getRating()==0
                ||fire.getRating()==0
                ||regular.getRating()==0
                ||health.getRating()==0){
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
            SupportModel model = new SupportModel();
            model.setLevel(activity.level);
            model.setRoom_id(activity.roomID);
            model.setCleanliness((int) cleanliness.getRating());
            model.setMaintenance((int) maintenance.getRating());
            model.setFm_response((int) fm.getRating());
            model.setSecurity_services((int) security.getRating());
            model.setEntrance_emergence((int) entrance.getRating());
            model.setExit_signs((int) clarity.getRating());
            model.setElevators((int) quality.getRating());
            model.setWayfinding((int) wayfinding.getRating());
            model.setConveniences((int) conveniences.getRating());
            model.setWater_supply((int) water.getRating());
            model.setFire_safety((int) fire.getRating());
            model.setUser_orientation((int) regular.getRating());
            model.setHealth((int) health.getRating());
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("building-support-ratings");
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
