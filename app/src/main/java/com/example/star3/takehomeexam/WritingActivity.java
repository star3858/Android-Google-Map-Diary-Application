package com.example.star3.takehomeexam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Locale;

public class WritingActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    double x_value;
    double y_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        final EditText nameText = (EditText) findViewById(R.id.editText2);
        final EditText contentsText = (EditText) findViewById(R.id.editText3);
        final Button saveButton = (Button) findViewById(R.id.save_button);

        //SQLite Helper 를 초기화 한다.
        LocationDB sqh = new LocationDB(this);
        // 읽고 쓸 수 있는 데이터베이스를 가져온다.
        final SQLiteDatabase sqdb = sqh.getWritableDatabase();

        ///////////////////////////////////////////
        //    1. 입력      //
        ///////////////////////////////////////////
        //방법 #1 : ContentValues 클래스를 사용한 입력.
        //입력할 테이블, 비어 있는 ContentValues을 전달 받았을경우 null값 처리할 컬럼 , ContentValues 객체
        // 방법 #2 : SQL 쿼리를 사용해 입력
        //String insertQuery = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (" + SQLiteHelper.NAME + ") VALUES ('jwei') ";
        //sqdb.execSQL(insertQuery);
        /////////////////
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put(LocationDB.NAME, nameText.getText().toString());
                cv.put(LocationDB.CONTENTS, contentsText.getText().toString());
                cv.put(LocationDB.POS_X, x_value);
                cv.put(LocationDB.POS_Y, y_value);

                // INSERT 메소드 호출
                sqdb.insert(LocationDB.TABLE_NAME, null, cv);
                Intent intent = new Intent(WritingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
       // finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GpsInfo gps = new GpsInfo(WritingActivity.this);

        if (gps.isGetLocation()) {
            double lat = gps.getLatitude();
            double log = gps.getLongitude();

            // Creating a Lating object for the current location
            LatLng pos = new LatLng(lat, log);

            String marker_title = String.format(Locale.ENGLISH, "위도: %f / ", lat) +
                    String.format(Locale.ENGLISH, "경도: %f", log);

            // 마커 설정
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(pos);  // 위도, 경도
            optFirst.title("현재 위치");
            optFirst.snippet(marker_title);
            mMap.addMarker(optFirst).showInfoWindow();

            // Showing the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 13));
        }

        // Add a marker in Sydney and move the camera
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                    x_value = latLng.latitude;
                    y_value = latLng.longitude;

                    String marker_title = String.format(Locale.ENGLISH, "Latitude: %f / ", x_value) +
                            String.format(Locale.ENGLISH, "Longitude: %f", y_value);

                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("선택 위치").snippet(marker_title));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
    }
}