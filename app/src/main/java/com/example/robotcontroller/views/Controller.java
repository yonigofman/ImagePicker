package com.example.robotcontroller.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.io.File;

public class Controller extends LinearLayout {


    public Controller(Context context) {
        super(context);
    }

    public Controller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Controller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Controller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    public void init(){

        File file = new File("file.java");
    }
}
