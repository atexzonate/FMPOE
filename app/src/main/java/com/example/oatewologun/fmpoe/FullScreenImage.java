package com.example.oatewologun.fmpoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.oatewologun.fmpoe.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class FullScreenImage extends AppCompatActivity {

    @BindView(R.id.image)
    PhotoView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        ButterKnife.bind(this);

        String level = getIntent().getStringExtra("level");
        if(level == null)
            return;

        if(level.equals(Constants.gLvl)){
            image.setImageResource(R.drawable.h13_g);
        }
        else if(level.equals(Constants.mLvl)){
            image.setImageResource(R.drawable.h13_m);
        }
        else if(level.equals(Constants.lvl1)){
            image.setImageResource(R.drawable.h13_l1);
        }
        else if(level.equals(Constants.lvl2)){
            image.setImageResource(R.drawable.h13_l2);
        }
        else if(level.equals(Constants.lvl3)){
            image.setImageResource(R.drawable.h13_l3);
        }
        else if(level.equals(Constants.lvl4)){
            image.setImageResource(R.drawable.h13_l4);
        }
        else if(level.equals(Constants.lvl5)){
            image.setImageResource(R.drawable.h13_l5);
        }
        else if(level.equals(Constants.lvl6)){
            image.setImageResource(R.drawable.h13_l6);
        }
    }
}
