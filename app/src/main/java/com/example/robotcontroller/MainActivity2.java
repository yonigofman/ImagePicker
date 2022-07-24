package com.example.robotcontroller;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yonigofman.imagepickerdialog.ImagePickedListener;
import com.yonigofman.imagepickerdialog.ImagePickerDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        initPermissionResultLauncher();
        requestPermission();


        Button button = findViewById(R.id.button);
         imageView = findViewById(R.id.imageView);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });



    }

    public  void openDialog(){
        ImagePickerDialog fragment =new ImagePickerDialog();
        fragment.setImagePickedListener(new ImagePickedListener() {
            @Override
            public void onImagePicked(Bitmap bitmap) {


                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onImagePicked(Uri uri) {

                imageView.setImageURI(uri);
            }
        });

        fragment.show(getSupportFragmentManager(),"onDetectedFrag");
    }



    ActivityResultLauncher<String[]> mPermissionResultLauncher;
    private boolean isStoragePermissionGranted = false;
    private boolean isCameraPermissionGranted = false;




    public  void initPermissionResultLauncher(){
        mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {

                if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null){

                    isStoragePermissionGranted = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);

                }

                if (result.get(Manifest.permission.CAMERA) != null){

                    isCameraPermissionGranted = result.get(Manifest.permission.CAMERA);

                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void requestPermission(){

        isStoragePermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;

        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;



        List<String> permissionRequest = new ArrayList<String>();

        if (!isStoragePermissionGranted){

            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        }

        if (!isCameraPermissionGranted){

            permissionRequest.add(Manifest.permission.CAMERA);

        }


        if (!permissionRequest.isEmpty()){
            mPermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));
        }


    }
}