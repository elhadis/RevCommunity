package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Holders.FidWorkAdabter;
import Model.KidsInfo;
import Model.PostingWork;

public class FindWorkActivity extends AppCompatActivity {
    private Button addWOrk;




    private Dialog loadingDialog, addWorkDialog;
    private EditText kidName,kidFamilyName,kidFamilyPhone;
    private Button sendKidInfo;
    DatabaseReference Ref;
    LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_find_work);
        kidName = findViewById(R.id.kid_name);
        kidFamilyPhone = findViewById(R.id.kid_family_phone);
        kidFamilyName   = findViewById(R.id.kid_Family_name);
        sendKidInfo = findViewById(R.id.kid_info);




        Ref = FirebaseDatabase.getInstance().getReference().child("Kids");


        loadingDialog = new Dialog(FindWorkActivity.this);
        loadingDialog.setContentView(R.layout.loading_progress);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        sendKidInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = kidName.getText().toString();
                String familyName = kidFamilyName.getText().toString();
                String familyPone = kidFamilyPhone.getText().toString();

                if (name.isEmpty()){
                    kidName.setError("Enter Name ");
                    return;
                }
                else  if (familyName.isEmpty()){
                    kidFamilyName.setError("Enter  the FamilyName ");
                    return;
                }
                else  if (familyPone.isEmpty()){
                    kidName.setError("Enter Family Phone ");
                    return;
                }
                else {

                    sendKidsInfo();
                }


           }
        });






    }

    private void sendKidsInfo() {


        String  currentDate;
        Calendar calendarKids = Calendar.getInstance();
        SimpleDateFormat kidDate = new SimpleDateFormat("dd.MM,yyyy");
        currentDate = kidDate.format(calendarKids.getTime());
        HashMap<String ,Object> kidData  = new HashMap<>();
        kidData.put("kidName",kidName.getText().toString());
        kidData.put("kidFamily",kidFamilyName.getText().toString());
        kidData.put("familyNum",kidFamilyPhone.getText().toString());
        kidData.put("date",currentDate);
        Ref.push().setValue(new KidsInfo(kidName.getText().toString(),kidFamilyName.getText().toString(),
                kidFamilyPhone.getText().toString(),currentDate)).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getBaseContext(),"Sending is Successfully",Toast.LENGTH_LONG).show();
                            kidName.setText("");
                            kidFamilyName.setText("");
                            kidFamilyPhone.setText("");
                        }

                    }
                });


    }

}




