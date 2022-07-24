package com.yonigofman.imagepickerdialog;

import android.graphics.Bitmap;
import android.net.Uri;

public interface ImagePickedListener {
    void onImagePicked(Bitmap bitmap);
    void onImagePicked(Uri uri);
}
