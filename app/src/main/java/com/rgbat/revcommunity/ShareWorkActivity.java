package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Model.Coors;

public class ShareWorkActivity extends AppCompatActivity {
    private EditText fullName,phoneNumber,coorsName;
    private Button sendInfo;
    DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_work);
        fullName = findViewById(R.id.full_name_coors);
        phoneNumber = findViewById(R.id.phone_number_coors);
        coorsName = findViewById(R.id.coors_name);
        sendInfo = findViewById(R.id.send_info);

        Ref = FirebaseDatabase.getInstance().getReference().child("Coors");

        sendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final   String full = fullName.getText().toString();
                final String phone =phoneNumber.getText().toString();
                final String coors =coorsName.getText().toString();
                if (full.isEmpty()){
                    fullName.setError("Enter Your Name");
                    return;
                }else if (phone.isEmpty()){
                    phoneNumber.setError("Enter Your Phone");
                    return;
                }else if (coors.isEmpty()){
                    coorsName.setError("Enter Coors Name");
                    return;
                }
                else {


                    SendDataFirebase();


                }


            }


        });
                


    }

    private void SendDataFirebase() {

        final  String currentDate;

        Calendar coorsDateTime = Calendar.getInstance();
        SimpleDateFormat coorsDate = new SimpleDateFormat("dd.MM,yyyy");
        currentDate = coorsDate.format(coorsDateTime.getTime());
        Ref =FirebaseDatabase.getInstance().getReference().child("Coors");
        HashMap<String,Object> coorsData = new HashMap<>();
        coorsData.put("fullName",fullName.getText().toString());
        coorsData.put("phone",phoneNumber.getText().toString());
        coorsData.put("coorsName",coorsName.getText().toString());
        coorsData.put("date",currentDate);
      Ref.push().setValue(new Coors(fullName.getText().toString(),phoneNumber.getText().toString(),
              coorsName.getText().toString(),currentDate)).addOnCompleteListener
              (new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {

              if (task.isSuccessful()){
                  Toast.makeText(getBaseContext(),"Thanks We Will Call You",Toast.LENGTH_LONG).show();
                  Intent intent = new Intent(ShareWorkActivity.this,MainActivity.class);
                  startActivity(intent);
              }

          }
      });

    }


}
