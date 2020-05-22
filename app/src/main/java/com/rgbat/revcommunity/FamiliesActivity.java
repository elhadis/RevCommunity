package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Model.Families;

public class FamiliesActivity extends AppCompatActivity {
    private EditText mealName,femaleName,kidsNumber,address,phone;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_families);
        mealName = findViewById(R.id.meal_partner_families);
        femaleName = findViewById(R.id.female_partner_families);
        kidsNumber= findViewById(R.id.families_kids_number);
        phone= findViewById(R.id.families_phone);
        address= findViewById(R.id.families_address);
        submit = findViewById(R.id.families_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (address.getText().toString().isEmpty()){
                    address.setError("Please Enter The Address");
                    return;
                }else   if (phone.getText().toString().isEmpty()){
                    phone.setError("Please Enter The Phone Number");
                    return;
                }
                else {

                    FamiliesInfo();
                }

            }
        });

    }

    private void FamiliesInfo() {


        final DatabaseReference Ref ;
        Ref = FirebaseDatabase.getInstance().getReference().child("Families Services");
        final String currentDate,currentTime;
        Calendar family = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("MM,dd,yyyy");
        currentDate = date.format(family.getTime());
        SimpleDateFormat time = new SimpleDateFormat("HH: a");
        currentTime = time.format(family.getTime());

        HashMap<String,Object> families = new HashMap<>();
        families.put("mealName",mealName);
        families.put("femaleName",femaleName);
        families.put("kids",kidsNumber);
        families.put("address",address);
        families.put("phone",phone);
        families.put("date",currentDate);
        families.put("time",currentDate);
        Ref.push().setValue(new Families(mealName.getText().toString(),femaleName.getText().toString(),kidsNumber.getText().toString(),
                address.getText().toString(),phone.getText().toString(),currentDate,currentTime))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getBaseContext(),"Sending Information is Successful",Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                    startActivity(intent);


                                }
                            }, 4000);
                        }

                    }
                });


    }
}
