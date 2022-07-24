# ImagePicker

![image](https://user-images.githubusercontent.com/62130401/180645465-578569f8-ea7e-448d-8669-2b131ce04dc1.png)
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
