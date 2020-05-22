package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Model.Helps;
import Model.PostingWork;

public class AdminActivity extends AppCompatActivity {
    private EditText fullNameHelps,phoneNumberHelps,visaHelps;
    private Button helpsButton;

   DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        fullNameHelps =findViewById(R.id.full_name_helps);
        phoneNumberHelps = findViewById(R.id.phone_number_helps);
        visaHelps = findViewById(R.id.visa_number_helps);
        helpsButton = findViewById(R.id.button_helps);


        Ref = FirebaseDatabase.getInstance().getReference().child("Helps");
        helpsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String fullName = fullNameHelps.getText().toString();
                String phone = phoneNumberHelps.getText().toString();
                String visa = visaHelps.getText().toString();
                if (fullName.isEmpty()){
                    fullNameHelps.setError("Enter Your Name");
                    return;
                }else  if (phone.isEmpty()){
                    phoneNumberHelps.setError("Enter Your Phone Number");
                    return;
                }else  if (visa.isEmpty()){
                    visaHelps.setError("Enter Your Visa Number");
                    return;
                }else {

                    findDataToHelps();


                }

            }
        });


    }

    private void findDataToHelps() {
        final  String currentTime,currentDate;

        Calendar helps = Calendar.getInstance();
        SimpleDateFormat helpDate = new SimpleDateFormat("dd,MM,yyyy");
        currentDate = helpDate.format(helps.getTime());
        Map<String,Object> map = new ArrayMap<>();
        map.put("fullName",fullNameHelps.getText().toString());
        map.put("phone",phoneNumberHelps.getText().toString());
        map.put("visa",visaHelps.getText().toString());
        map.put("date",currentDate);
        Ref.push().setValue(new Helps(fullNameHelps.getText().toString(),phoneNumberHelps.getText().toString(),
                visaHelps.getText().toString(),currentDate)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(getBaseContext(),"Sending Information is Successful",Toast.LENGTH_LONG).show();

                    }
                }, 2000);

            }




        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }
}


