package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Magnifier;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rgbat.revcommunity.R;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

public class PostProductActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 102;
    private ImageView postImage;
    private EditText postProductPrice,postProductPhone;
    private Button postBtn;
    private static final int Gallery_Pick = 1;
    private Uri ImageUri;
    private StorageReference postImagesReference;
    private DatabaseReference productRef;
    private String price,phone;
    private String saveCurrentDate,saveCurrentTime,postRandomName;
    private String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);
        postBtn = findViewById(R.id.post_new_product);
        postProductPrice= findViewById(R.id.post_product_price);
        postProductPhone = findViewById(R.id.post_product_phone);
        postImage = findViewById(R.id.post_product_image);

        postImagesReference = FirebaseStorage.getInstance().getReference();
        productRef = FirebaseDatabase.getInstance().getReference().child("Products");

        postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();

            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateHouseForPost();
            }


        });

    }


    private void validateHouseForPost() {

        price = postProductPrice.getText().toString();
        phone = postProductPhone.getText().toString();
        if (price.isEmpty()){
            postProductPrice.setError("Write The Product Price Please");
            return;
        }else  if (phone.isEmpty()){
            postProductPhone.setError("Write The Phone Number");
            return;
        }


        else {


            StoringImageToStorege();

        }


    }


    private void StoringImageToStorege() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd,MM,yyyy");
        saveCurrentDate = date.format(calendar.getTime());

        SimpleDateFormat time = new SimpleDateFormat("HH:mm a");
        saveCurrentTime = time.format(calendar.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

        final    StorageReference fillPath = postImagesReference.child("Post Product")
                .child(ImageUri.getLastPathSegment()+postRandomName +".jpg");

        final UploadTask uploadTask = fillPath.putFile(ImageUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override

            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(getBaseContext(),"Product Image uploaded Successfully",Toast.LENGTH_LONG)
                        .show();
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        downloadUrl = fillPath.getDownloadUrl().toString();
                        return fillPath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {


                        if (task.isSuccessful()){

                            downloadUrl = task.getResult().toString();
//                            Toast.makeText(getBaseContext(),"Product image save Successfully",Toast.LENGTH_LONG).
//                                    show();

                            SavingPostInfo();


                        }

                    }
                });

            }
        });




    }

    private void SavingPostInfo() {

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HashMap productMap = new HashMap();
                productMap.put("price",postProductPrice.getText().toString());
                productMap.put("phone",postProductPhone.getText().toString());
                productMap.put("productimage",downloadUrl);
                productMap.put("date",saveCurrentDate);
                productMap.put("time",saveCurrentTime);
                productRef.child(postRandomName).updateChildren(productMap).
                        addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()){
                                    postImage.setImageURI(null);
                                    postProductPrice.setText("");
                                    postProductPhone.setText("");
                                }
                                else {
                                    Toast .makeText(getBaseContext(),"Error   ????????",
                                            LENGTH_LONG).show();
                                }
                            }
                        });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    private void openGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,Gallery_Pick);
        //  askCameraPermation();


    }
//
//    private void askCameraPermation() {
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.
//                PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},101);
//        }else {
//            openCamera();
//        }
//    }
//
//    private void openCamera() {
//
//       Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//       startActivityForResult(camera, 101);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 101){
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//
//
//            }else {
//
//                Toast.makeText(getBaseContext(),"Not Successful", LENGTH_LONG).show();
//            }
    //      }
    //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_Pick&& resultCode == RESULT_OK && data != null){

            ImageUri = data.getData();
            postImage.setImageURI(ImageUri);
//            Bitmap image = (Bitmap) data.getExtras().get("data");
//            postImage.setImageBitmap(image);


        }
    }
}
