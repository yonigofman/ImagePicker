package com.yonigofman.imagepickerdialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class ImagePickerDialog extends DialogFragment {


    Button btn_camera, btn_gallery;
    private ImagePickedListener imagePickedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.image_dialog,null);
        btn_camera = view.findViewById(R.id.btn_camera);
        btn_gallery = view.findViewById(R.id.btn_gallery);
        init();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(intent);
            }
        });


        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryLauncher.launch("image/*");
            }
        });
    }


    public void setImagePickedListener(ImagePickedListener imagePickedListener) {
        this.imagePickedListener = imagePickedListener;
    }

    ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {

            if(result!=null) {
                imagePickedListener.onImagePicked(result);
                dismiss();
            }
        }
    });

    ActivityResultLauncher cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            if(result.getResultCode()==getActivity().RESULT_OK && result.getData() != null){
                Bundle bundle = result.getData().getExtras();
                Bitmap bitmap = (Bitmap)bundle.get("data");
                imagePickedListener.onImagePicked(bitmap);

                dismiss();

            }

        }

    });

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    private void init(){
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);



        }
    }
}
