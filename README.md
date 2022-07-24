# ImagePicker


## How to implement in your code:


1. create new instance of ImagePickerDialog

```java

ImagePickerDialog imagePicker = new ImagePickerDialog();

```

2. set setImagePickedListener with callback

```java

      imagePicker.setImagePickedListener(new ImagePickedListener() {
            @Override
            public void onImagePicked(Bitmap bitmap) {
              
              //get bitmap from camera
                
            }

            @Override
            public void onImagePicked(Uri uri) {

                //get uri from gallery
            }
        });

```

3. show

```java
    imagePicker.show(getSupportFragmentManager(),"imagePicker");
```
