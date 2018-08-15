package com.example.oatewologun.fmpoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oatewologun.fmpoe.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloorPlan extends AppCompatActivity {
    String level;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.roomId)
    TextView roomId;
    @BindView(R.id.complaint)
    TextView complaint;
    @BindView(R.id.form)
    TextView form;
    @BindView(R.id.image)
    ImageView image;
    String selectedRoomId;
    @BindView(R.id.buttonsPanel)
    LinearLayout buttonsPanel;
    int arrSize;
    @BindView(R.id.roomIdPanel)
    RelativeLayout roomIdPanel;
    @BindView(R.id.formPanel)
    RelativeLayout formPanel;
    @BindView(R.id.orTxt)
    TextView orTxt;
    @BindView(R.id.enterRoomId)
    EditText enterRoomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);
        ButterKnife.bind(this);

        level = getIntent().getStringExtra("level");
        title.setText(level);
        if (level.equals(Constants.gLvl)
                || level.equals(Constants.mLvl)
                || level.equals(Constants.lvl1)
                || level.equals(Constants.lvl5)) {
            roomIdPanel.setVisibility(View.GONE);
            orTxt.setVisibility(View.GONE);
            formPanel.setVisibility(View.GONE);
        }

        if (level.equals(Constants.gLvl)) {
            image.setImageResource(R.drawable.h13_g_);
        } else if (level.equals(Constants.mLvl)) {
            image.setImageResource(R.drawable.h13_m_);
        } else if (level.equals(Constants.lvl1)) {
            image.setImageResource(R.drawable.h13_l1);
        } else if (level.equals(Constants.lvl2)) {
            image.setImageResource(R.drawable.h13_l2_);
            String[] temp = Constants.getLevel2_Rooms();
            arrSize = temp.length;
        } else if (level.equals(Constants.lvl3)) {
            image.setImageResource(R.drawable.h13_l3_);
            String[] temp = Constants.getLevel3_Rooms();
            arrSize = temp.length;
        } else if (level.equals(Constants.lvl4)) {
            image.setImageResource(R.drawable.h13_l4_);
            String[] temp = Constants.getLevel4_Rooms();
            arrSize = temp.length;
        } else if (level.equals(Constants.lvl5)) {
            image.setImageResource(R.drawable.h13_l5_);
        } else if (level.equals(Constants.lvl6)) {
            image.setImageResource(R.drawable.h13_l6_);
            String[] temp = Constants.getLevel6_Rooms();
            arrSize = temp.length;
        }
    }

    public void selectRoomId(View view) {
        String[] options = new String[arrSize];
        if (level.equals(Constants.lvl2)) {
            options = Constants.getLevel2_Rooms();
        } else if (level.equals(Constants.lvl3)) {
            options = Constants.getLevel3_Rooms();
        } else if (level.equals(Constants.lvl4)) {
            options = Constants.getLevel4_Rooms();
        } else if (level.equals(Constants.lvl6)) {
            options = Constants.getLevel6_Rooms();
        }

        showRoomsIdsPopup(options);
    }

    private void showRoomsIdsPopup(final String[] options) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Room ID");
        builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedRoomId = options[which];
                roomId.setText(selectedRoomId);
                Log.e("tag", "selected: " + selectedRoomId);
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    public void complaint(View view) {
        if (selectedRoomId == null && enterRoomId.getText().toString().isEmpty()) {
            Toast.makeText(FloorPlan.this, "Please select Room ID first!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(FloorPlan.this, ComplaintForm.class);
        intent.putExtra("level", level);
        if(selectedRoomId != null)
            intent.putExtra("room_id", selectedRoomId);
        else
            intent.putExtra("room_id", enterRoomId.getText().toString());
        startActivity(intent);
    }

    public void form(View view) {
        if (selectedRoomId == null && enterRoomId.getText().toString().isEmpty()) {
            Toast.makeText(FloorPlan.this, "Please select Room ID first!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(selectedRoomId.contains("Lift") || enterRoomId.getText().toString().toLowerCase().contains("lift")){
            Intent intent = new Intent(FloorPlan.this, FillPOEForm.class);
            intent.putExtra("level", level);
            if(selectedRoomId != null)
                intent.putExtra("room_id", selectedRoomId);
            else
                intent.putExtra("room_id", enterRoomId.getText().toString());
            intent.putExtra("form", "Elevator");
            startActivity(intent);
            return;
        }
        if(selectedRoomId.contains("Lab") || enterRoomId.getText().toString().toLowerCase().contains("lab")
                ||enterRoomId.getText().toString().toLowerCase().contains("workshop")){
            Intent intent = new Intent(FloorPlan.this, FillPOEForm.class);
            intent.putExtra("level", level);
            if(selectedRoomId != null)
                intent.putExtra("room_id", selectedRoomId);
            else
                intent.putExtra("room_id", enterRoomId.getText().toString());
            intent.putExtra("form", "Workshop/Lab");
            startActivity(intent);
            return;
        }
        if(selectedRoomId.contains("Toilet") || enterRoomId.getText().toString().toLowerCase().contains("toilet")){
            Intent intent = new Intent(FloorPlan.this, FillPOEForm.class);
            intent.putExtra("level", level);
            if(selectedRoomId != null)
                intent.putExtra("room_id", selectedRoomId);
            else
                intent.putExtra("room_id", enterRoomId.getText().toString());
            intent.putExtra("form", "Toilet");
            startActivity(intent);
            return;
        }

        final String[] options = Constants.getPOEOptions();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Form");
        builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(FloorPlan.this, FillPOEForm.class);
                intent.putExtra("level", level);
                if(selectedRoomId != null)
                    intent.putExtra("room_id", selectedRoomId);
                else
                    intent.putExtra("room_id", enterRoomId.getText().toString());
                intent.putExtra("form", options[which]);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    @OnClick(R.id.image)
    public void onViewClicked() {
        Intent intent = new Intent(this, FullScreenImage.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}
