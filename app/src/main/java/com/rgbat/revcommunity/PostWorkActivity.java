package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rgbat.revcommunity.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Model.PostingWork;

public class PostWorkActivity extends AppCompatActivity {

    private EditText workCity,workEmployerNum,workType;
    private Button postingBtn;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_work);
        workType = findViewById(R.id.work_type);
        workCity = findViewById(R.id.work_city);
        workEmployerNum = findViewById(R.id.employer_num);
        postingBtn = findViewById(R.id.post_work_information);


        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_progress);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        postingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type =  workType.getText().toString();
                String city =  workCity.getText().toString();
                String employer =  workEmployerNum.getText().toString();
                if (type.isEmpty()){
                    workType.setError("Enter The Work Type");
                    return;
                }
                else if (city.isEmpty()){
                    workCity.setError("Enter The Work Place");
                    return;
                } else if (employer.isEmpty()){
                    workEmployerNum.setError("Enter The Employer Number");
                    return;
                }
                else {
                    loadingDialog.show();

                    final String currentTime,currentDate;
                    Calendar workTime = Calendar.getInstance();
                    SimpleDateFormat workDate = new SimpleDateFormat("dd,MM,yyyy");
                    currentDate = workDate.format(workTime.getTime());

                    SimpleDateFormat workHor  =new SimpleDateFormat("HH:mm: a");
                    currentTime = workHor.format(workTime.getTime());

                    DatabaseReference Ref;
                    Ref = FirebaseDatabase.getInstance().getReference().child("Posting");
                    HashMap<String,Object> posting = new HashMap<>();
                    posting.put("workPlace" ,workCity.getText().toString());
                    posting.put("workType" ,workType.getText().toString());
                    posting.put("workEmployerNum" ,workEmployerNum.getText().toString());
                    posting.put("date",currentDate);
                    posting.put("time",currentTime);
                    Ref.push().setValue(new PostingWork(type,city,employer,currentDate,currentTime))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                        loadingDialog.dismiss();
                                        Toast.makeText(getBaseContext(),"Posting is Successful",
                                                Toast.LENGTH_LONG).show();
                                        workType.setText("");
                                        workCity.setText("");
                                        workEmployerNum.setText("");
//
//                                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
//                                        startActivity(intent);
                                    }
                                    else {
                                        loadingDialog.dismiss();
                                        Toast.makeText(getBaseContext(),"Posting is Not Successful please Try Again",
                                                Toast.LENGTH_LONG).show();
                                    }

                                }
                            });



                }

            }
        });
    }
}
