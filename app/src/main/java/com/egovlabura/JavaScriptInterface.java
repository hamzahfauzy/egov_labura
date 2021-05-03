package com.egovlabura;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    JavaScriptInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String Hello()
    {
        return "Hello World";
    }

    public void openLocalCamera()
    {
//        try {
//            mContext.startActivity(new Intent(MainActivity.this, CameraActivity.class));
//        } catch(Exception ex) {
//
//        }
    }

    public void calculateLocation()
    {
        GPSTracker gps = new GPSTracker(mContext);

        // Check if GPS enabled
        if(gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();


            // \n is for new line
            Toast.makeText(mContext, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }
    }
}
