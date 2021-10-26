package com.egovlabura;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
    Context mContext;
    GPSTracker gps;
    double latitude, longitude;

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

    @JavascriptInterface
    public String getLocation()
    {
        String ret = "{}";
        try {
            GPSTracker gps = new GPSTracker(mContext);
            Location location;
            // Check if GPS enabled
            if(gps.canGetLocation()) {
                location = gps.getLocation();
                if(location.isFromMockProvider())
                    ret = "{\"status\":\"error\",\"message\":\"Error Code Mock Location\",\"data\":null}";
                else
                {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    ret = "{\"status\":\"success\",\"message\":\"Success Get Location\",\"data\":{\"lat\":"+latitude+",\"lng\":"+longitude+"}}";
//                    Toast.makeText(mContext, "Lokasi Anda - \nLat: " + latitude + "\nLong: " + longitude + "\nAkurasi: " + gps.getLocation().getAccuracy(), Toast.LENGTH_LONG).show();
                }
            } else {
                ret = "{\"status\":\"error\",\"message\":\"Get Location Fail\",\"data\":null}";
//                gps.showSettingsAlert();
            }
        }catch (Exception e){
            ret = "{\"status\":\"error\",\"message\":\"GPS Service Error\",\"data\":null}";
//            Toast.makeText(mContext, "Error GPS Service. Silahkan Hubungi Administrator", Toast.LENGTH_LONG).show();
        }

        return ret;
    }

    @JavascriptInterface
    public void showLocation()
    {
        try {
            GPSTracker gps = new GPSTracker(mContext);
            Location location;
            // Check if GPS enabled
            if(gps.canGetLocation()) {
                location = gps.getLocation();
                if(location.isFromMockProvider())
                    Toast.makeText(mContext, "Error Code Mock Location", Toast.LENGTH_LONG).show();
                else
                {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    Toast.makeText(mContext, "Lokasi Anda - \nLat: " + latitude + "\nLong: " + longitude + "\nAkurasi: " + gps.getLocation().getAccuracy(), Toast.LENGTH_LONG).show();
                }
            } else {
                gps.showSettingsAlert();
            }
        }catch (Exception e){
            Toast.makeText(mContext, "Error GPS Service. Silahkan Hubungi Administrator", Toast.LENGTH_LONG).show();
        }
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
