package com.example.igx.problem1;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener/* implements Something1, Something2 */ {

    SensorManager sm;
    Sensor gyro, gravity, accel, linear_accel;
    String grav_str, gyro_str, accel_str, linear_str;
    Double latitude, longitude;
    String sensor_str, location_str;
    EditText txtUriString;
    Button btn_sendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);

        sm = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        linear_accel = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_selectedType.setText("LOCATION");
                location_str = "Latitude : " + latitude + "\nLongitude : " + longitude;
                text_selectedData.setText(location_str);
            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_selectedType.setText("SENSORS");
                sensor_str = grav_str + accel_str + linear_str + gyro_str;
                text_selectedData.setText(sensor_str);
            }
        });


        // 메시지 전송
        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        try {
            txtUriString = (EditText)findViewById(R.id.edit_phoneNumber);
            btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);
            btn_sendMessage.setOnClickListener(new MainActivity.MyClickHandler());
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    } // onCreate 끝

    // 메시지 전송을 위한 클릭 핸들러
    public class MyClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            try {
                String myData = "tel:" + txtUriString.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(myData));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 센서 리스너에 등록
        sm.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, linear_accel, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 센서 리스너 해제
        sm.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                grav_str = "GRAVITY => X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2] + "\n";
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accel_str = "ACCELEROMETER => X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2] + "\n";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                linear_str = "LINEAR_ACCELERATION => X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2] + "\n";
                break;
            case Sensor.TYPE_GYROSCOPE:
                gyro_str = "GYROSCOPE => X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2] + "\n";
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}






