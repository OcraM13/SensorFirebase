package com.example.sensoresfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sensoresfirebase.Modelos.ModeloAcelerometro;
import com.example.sensoresfirebase.Modelos.ModeloGiroscopio;
import com.example.sensoresfirebase.Modelos.ModeloLuz;
import com.example.sensoresfirebase.Modelos.ModeloSensorAproximidad;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class MedirSen extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    private String sensorTxt;

    private TextView txtSensor;
    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;

    private Button btnComen;

    private boolean apagado = true;

    private int contador = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medir_sen);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorTxt = getIntent().getStringExtra("sensor");

        final DecimalFormat f = new DecimalFormat("#.00000");

        txtSensor = findViewById(R.id.textSen);
        txtX = findViewById(R.id.textX);
        txtY = findViewById(R.id.textY);
        txtZ = findViewById(R.id.textZ);

        btnComen = findViewById(R.id.btnComen);

        apagado = true;

        iniciarFirebase();

        switch (sensorTxt){
            case "G":
                txtSensor.setText("Giroscopio");
                sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                break;

            case "A":
                txtSensor.setText("Acelerometro");
                sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                break;

            case "SA":
                txtSensor.setText("Sensor de Aproximidad");
                sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                break;

            case "T":
                txtSensor.setText("Luz");
                sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                break;
        }

        if(sensor == null){
            Toast.makeText(this,"No tienes este sensor", Toast.LENGTH_LONG);
            finish();
        }

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(sensorTxt.equals("G")){
                    txtX.setText("X -" + String.valueOf(f.format(event.values[0])));
                    txtY.setText("Y -" + String.valueOf(f.format(event.values[1])));
                    txtZ.setText("Z -" + String.valueOf(f.format(event.values[2])));

                    ModeloGiroscopio giroscopio = new ModeloGiroscopio(Double.valueOf(f.format(event.values[0])),Double.valueOf(f.format(event.values[1])),Double.valueOf(f.format(event.values[2])));

                    databaseReference.child("Giroscopio").setValue(giroscopio);



                }

                if(sensorTxt.equals("A")){
                    txtX.setText("X -" +String.valueOf(f.format(event.values[0])));
                    txtY.setText("Y -" +String.valueOf(f.format(event.values[1])));
                    txtZ.setText("Z -" +String.valueOf(f.format(event.values[2])));

                    ModeloAcelerometro acelerometro = new ModeloAcelerometro(Double.valueOf(f.format(event.values[0])),Double.valueOf(f.format(event.values[1])),Double.valueOf(f.format(event.values[2])));

                    databaseReference.child("Acelerometro").setValue(acelerometro);


                }

                if(sensorTxt.equals("SA")){
                    txtX.setText("");
                    txtY.setText(String.valueOf(event.values[0]) + "cm");
                    txtZ.setText("");

                    ModeloSensorAproximidad aproximidad = new ModeloSensorAproximidad(Double.valueOf(f.format(event.values[0])));

                    databaseReference.child("SensorAproximidad").setValue(aproximidad);
                }

                if(sensorTxt.equals("T")){
                    txtX.setText("");
                    txtY.setText(String.valueOf(event.values[0]));
                    txtZ.setText("");

                    ModeloLuz luz = new ModeloLuz(Double.valueOf(f.format(event.values[0])));

                    databaseReference.child("NivelLuz").setValue(luz);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        btnComen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apagado){
                    apagado = false;
                    btnComen.setText("Detener");
                    sensorManager.registerListener(sensorEventListener,sensor,1000000);
                }else{
                    apagado = true;
                    btnComen.setText("Comenzar");
                    sensorManager.unregisterListener(sensorEventListener);
                }
            }
        });

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        apagado = true;
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();

        apagado = true;
        sensorManager.unregisterListener(sensorEventListener);
    }

}
