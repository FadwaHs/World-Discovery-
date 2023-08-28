package com.travelchatapp.pfe;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.location.Location;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
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

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.Objects;


public class AddPhoto2 extends Fragment {

    private View view;

    private TextView addPhotoUpload;
    private AddPhotoDB addPhotoDB;
    private Bitmap bitmap;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private ResultReceiver resultReceiver;
    private EditText addPhotoTitle;
    private EditText addPhotoDescription;
    private EditText addPhotoLocation;
    public ImageView phototoupload;
    private Spinner addPhotoCategory;

    public AddPhoto2(Bitmap b)
    {
        this.bitmap=b;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.add_photo_next, container, false);
        resultReceiver=new AdressResultReceiver(new Handler());
        initialiseVar();

        clickMethods();

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()),R.array.categories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addPhotoCategory.setAdapter(adapter);
        return view;
    }

    private void initialiseVar() {
        addPhotoUpload = view.findViewById(R.id.addPhotoUpload);
        phototoupload=view.findViewById(R.id.bitmap);
        phototoupload.setImageBitmap(bitmap);
        addPhotoTitle = view.findViewById(R.id.addPhotoTitle);
        addPhotoDescription = view.findViewById(R.id.addPhotoDescription);
        addPhotoLocation = view.findViewById(R.id.addPhotoLocation);
        addPhotoCategory = view.findViewById(R.id.addPhotoCategory);


        vide();
    }

    private void clickMethods() {
        addPhotoUpload.setOnClickListener(view -> {
            if (bitmap != null && !addPhotoTitle.getText().toString().isEmpty() && !addPhotoDescription.getText().toString().isEmpty()  &&
                    !addPhotoLocation.getText().toString().isEmpty()  && !addPhotoCategory.getSelectedItem().toString().isEmpty() ) {

                addPhotoDB = new AddPhotoDB();
                addPhotoDB.execute(bitmap,addPhotoTitle.getText().toString(),addPhotoDescription.getText().toString(),
                        addPhotoLocation.getText().toString(),addPhotoCategory.getSelectedItem().toString());
                vide();
            }
        });
        addPhotoLocation.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (addPhotoLocation.getRight() - addPhotoLocation
                            .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (ContextCompat.checkSelfPermission(
                                getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(getActivity()
                                    ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
                        }
                        else
                        {
                            getCurrentLocation();
                        }

                        return true;
                    }
                }
                return false;
            }

        });
    }






    void vide(){
        addPhotoTitle.setText("");
        addPhotoDescription.setText("");
        addPhotoLocation.setText("");
        EditText city = view.findViewById(R.id.city);
        city.setText("");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length>0)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
            else
            {
                Toast.makeText(getContext(),"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation()
    {

        LocationRequest locationRequest=new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(getActivity()).requestLocationUpdates(locationRequest
                ,new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getActivity())
                                .removeLocationUpdates(this);
                        if(locationResult!=null && locationResult.getLocations().size()>0)
                        {
                            int latestlocationIndex=locationResult.getLocations().size()-1;
                            double latitude=
                                    locationResult.getLocations().get(latestlocationIndex).getLatitude();
                            double longitude=
                                    locationResult.getLocations().get(latestlocationIndex).getLongitude();

                            Location location=new Location("poviderNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            fetchAdressFromLatLong(location);
                        }

                    }
                }, Looper.getMainLooper());
    }


    private void fetchAdressFromLatLong(Location location)
    {
        Intent intent=new Intent(getContext(),FetchAdressIntentService.class);
        intent.putExtra(Constants.RECEIVER,resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA,location);
        getContext().startService(intent);
    }
    //class pour recevoir les resultats d'adresse
    private class AdressResultReceiver extends ResultReceiver
    {
        public AdressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode==Constants.SUCCESS_RESULT)
            {
                addPhotoLocation.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            }
            else
            {
                Toast.makeText(getContext(),resultData.getString(Constants.RESULT_DATA_KEY),Toast.LENGTH_SHORT).show();
            }

        }
    }




}
