package me.wooz.mobile.android.app.sinisters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.dto.NearPlace;
import me.wooz.mobile.android.widgets.RecyclerItemClickListener;

public class SendLocationActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SendLocationActivity";
    private static final int CODE_PERMISSION_FINE_LOCATION = 0;

    private SlidingUpPanelLayout slidingLayout;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private RecyclerView nearPlacesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        slidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
        slidingLayout.addPanelSlideListener(onPanelSlideListener);

        nearPlacesView = (RecyclerView) findViewById(R.id.rv_near_places);
        nearPlacesView.setHasFixedSize(true);
        nearPlacesView.setItemAnimator(new DefaultItemAnimator());
        nearPlacesView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, nearPlacesView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nearPlacesView.setLayoutManager(layoutManager);


        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        loadNearbyPlaces();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().selectedPosition(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "Connection Failure");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CODE_PERMISSION_FINE_LOCATION: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadNearbyPlaces();
                }
                break;
            }
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    private SlidingUpPanelLayout.PanelSlideListener onPanelSlideListener = new SlidingUpPanelLayout.PanelSlideListener() {

        @Override
        public void onPanelSlide(View panel, float slideOffset) {

        }

        @Override
        public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

        }
    };

    private void loadNearbyPlaces() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    CODE_PERMISSION_FINE_LOCATION);
            return;
        }

        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull  PlaceLikelihoodBuffer likelyPlaces) {
                List<NearPlace> nearPlaces = new ArrayList<>();
                for(PlaceLikelihood placeLikelihood : likelyPlaces) {
                    NearPlace place = new NearPlace();
                    place.setName(placeLikelihood.getPlace().getName().toString());
                    nearPlaces.add(place);
                    Log.d(TAG, placeLikelihood.getPlace().getName().toString());
                }
                likelyPlaces.release();

                // Set up near places
                NearPlacesAdapter adapter = new NearPlacesAdapter(nearPlaces);
                nearPlacesView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
