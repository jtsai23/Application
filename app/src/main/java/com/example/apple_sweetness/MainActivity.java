package com.example.apple_sweetness;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 0;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Camera
        Button CameraBtn = findViewById(R.id.Camera);
        CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CameraIntent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(CameraIntent);
            }
        });

        //Album, Gallery
        Button AlbumBtn = findViewById(R.id.Album);
        AlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_select);
                imageView = findViewById(R.id.appleImg);

                ImagefromGallery();
                textViewClick();
            }

        });
    }


    //Album
    private void ImagefromGallery() {
        Intent AlbumIntent = new Intent();
        AlbumIntent.setType("image/*");
        AlbumIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(AlbumIntent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try{
                    Uri uri = data.getData();
                    Glide.with(getApplicationContext()).load(uri).into(imageView);
                }catch (Exception e){

                }
            }else if(resultCode ==RESULT_CANCELED){ //cancel code

            }
        }
    }

    private void textViewClick(){
        //back
        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagefromGallery();

            }
        });

        //start
        TextView start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StartIntent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(StartIntent);
            }
        });
    }
}