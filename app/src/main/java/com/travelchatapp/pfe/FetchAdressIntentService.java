package com.travelchatapp.pfe;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FetchAdressIntentService extends IntentService {

    private ResultReceiver resultReceiver;

    public FetchAdressIntentService()
    {
        super("FetchAdressIntentService");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null)
        {
            String errorMessage="";
            resultReceiver=intent.getParcelableExtra(Constants.RECEIVER);
            Location location=intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
            if (location==null)
            {
                return;
            }
            Geocoder geocoder=new Geocoder(this, Locale.getDefault());
            List<Address> address = null;
            try {
                address=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            }catch (Exception e)
            {
                errorMessage=e.getMessage();
            }
            if (address==null || address.isEmpty())
            {
                deliverResultToReceiver(Constants.FAILURE_RESULT,errorMessage);
            }
            else
            {
                Address address1= address.get(0);
                ArrayList<String> adressFragment = new ArrayList<>();
                for (int i=0;i<= address1.getMaxAddressLineIndex();i++)
                {
                    adressFragment.add(address1.getAddressLine(i));
                }
                deliverResultToReceiver(Constants.SUCCESS_RESULT, TextUtils.join(
                        Objects.requireNonNull(System.getProperty("line.separator"))
                        ,adressFragment
                ));
            }
        }
    }
    private void deliverResultToReceiver(int resultCode,String adressMessage)
    {
        Bundle bundle=new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY,adressMessage);
        resultReceiver.send(resultCode,bundle);
    }
}
