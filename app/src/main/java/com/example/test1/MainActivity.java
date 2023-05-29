package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.hardware.camera2.CameraManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean on = false;
    TextView t;
    Switch s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = (Switch) findViewById(R.id.switch1);
        t = (TextView) findViewById(R.id.textView);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraManager c = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                try {
                    String[] cid = c.getCameraIdList();
                    if(on) {
                        Intent phone = new Intent(Intent.ACTION_SEND);
                        startActivity(phone);
                        t.setText("OFF");
                        on = false;
                    }
                    else {
                        t.setText("ON");
                        on = true;
                    }
                    c.setTorchMode(cid[1],on);
                } catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
