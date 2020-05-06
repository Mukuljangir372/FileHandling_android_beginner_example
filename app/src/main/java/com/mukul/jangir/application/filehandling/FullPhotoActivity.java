package com.mukul.jangir.application.filehandling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class FullPhotoActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        imageView =(ImageView) findViewById(R.id.fullImage);

        Intent intent = getIntent();
        //uri means Uniform resouce identifer
        Uri uri = intent.getData();
        imageView.setImageURI(uri);


    }
}
