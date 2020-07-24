package com.isroot.wol_android;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.location.Geofence;

import static com.isroot.wol_android.util.Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS;

public class MainViewModel extends AndroidViewModel {
        public MainViewModel(@NonNull Application application){
            super(application);
        }




    private Geofence buildGeofence(String requestId, double lat, double lon, float radius){
        return new Geofence.Builder()
                .setRequestId(requestId)
                .setCircularRegion(lat, lon, radius)
                .setExpirationDuration(GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL)
                .build();
    }
}
