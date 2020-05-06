package com.mukul.jangir.application.filehandling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demonstration of saving or load image to internal storage
 */
public class MainActivity extends AppCompatActivity {
    private FileOutputStream fileOutputStream = null;
    private FileInputStream fileInputStream;
    private Button saveButton,loadButton;
    private Bitmap bitmap;
    private ImageView imageView;
    private  Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = (Button) findViewById(R.id.save);
        loadButton= (Button) findViewById(R.id.load);
        imageView = (ImageView) findViewById(R.id.image);


        //saving image to internal storage
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawable = getResources().getDrawable(R.drawable.tony);
                bitmap = ((BitmapDrawable) drawable).getBitmap();
                saveToInternalStorage(bitmap);
            }
        });

        //load image from internal storage
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromInternalStorage();
            }
        });
      //adding listener to imageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(getApplicationContext(),FullPhotoActivity.class);
              Uri uri = Uri.parse("android.resource://"+ getApplicationContext().getPackageName()+"/drawable/tony.jpg");
              intent.setData(uri);
              startActivity(intent);

                    
            }
        });

    }
    private void saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"tony"+".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void loadImageFromInternalStorage() {
       ContextWrapper contextWrapper = new ContextWrapper(getBaseContext());
       File directory = contextWrapper.getDir("imageDir",Context.MODE_PRIVATE);
       File myPath = new File(directory,"tony.jpg");
       FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myPath);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }


    }
}
