package com.example.android.dr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE=1;
    ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mybotton = (Button) findViewById(R.id.mybotton);
        myImage=(ImageView) findViewById(R.id.myImage);

        if (!hasCamera())
            mybotton.setEnabled(false);


    }
    //check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public void launchCamera(View view){
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take picture and pass along to oonActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
  protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            //get the photo
            Bundle extras=data.getExtras();
            Bitmap photo =(Bitmap) extras.get("data");
            myImage.setImageBitmap(photo);
        }
  }


}
