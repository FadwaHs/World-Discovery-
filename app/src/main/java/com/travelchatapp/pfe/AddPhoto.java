package com.travelchatapp.pfe;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.InputStream;

public class AddPhoto extends Fragment {

    private View view;
    private ImageView addPhotoPhoto;
    private  TextView next;
    private Bitmap bitmap = null;
    private final int CODE = 3514;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.add_photo, container, false);
        initialiseVar();
        pickPhoto();
        clickMethods();

        return view;
    }

    private void initialiseVar() {
        addPhotoPhoto = view.findViewById(R.id.addPhotoPhoto);
        next=view.findViewById(R.id.nextFragment);

        vide();
    }

    private void clickMethods() {
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "choose a picture" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    void pickPhoto() {
        addPhotoPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("images/*");
                startActivityForResult(Intent.createChooser(intent, ""), CODE);

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            try {
                InputStream inputStream = Home.getInstance().getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);

                addPhotoPhoto.setImageBitmap(bitmap);

                //Toast.makeText(getContext(), bitmap.getWidth() + "\n" + bitmap.getHeight(), Toast.LENGTH_SHORT).show();
                addPhotoPhoto.setPadding(0,0,0,0);
                next.setEnabled(true);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.homeFragment, new AddPhoto2(bitmap));
                        fr.commit();
                    }
                });

                File file = new File(data.getData().getPath());
                ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
                int ori = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                Toast.makeText(getContext(), "" + ori, Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {

            }
        }
    }

    void vide(){
        addPhotoPhoto.setImageResource(R.drawable.photo);
    }




}
