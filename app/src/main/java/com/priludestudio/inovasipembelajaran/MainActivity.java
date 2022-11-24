package com.priludestudio.inovasipembelajaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btTujuan=(Button) findViewById(R.id.bt_tujuan);
        Button btPemantik = (Button) findViewById(R.id.bt_pemantik);

        btTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,TujuanActivity.class);
                startActivity(i);
            }
        });

        btPemantik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, PemantikActivity.class);
                startActivity(i);
            }
        });
    }
}