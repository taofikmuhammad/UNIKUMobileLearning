package com.priludestudio.inovasipembelajaran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class PemantikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan_pemantik);

        setTitle("Pertanyaan Pemantik");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView tvIsi = (TextView) findViewById(R.id.tvIsi);
        DBMateri dbMateri = new DBMateri(PemantikActivity.this);

        if (dbMateri.isNull())
        {
            Log.d("test","kosong");
        }else
        {
            Log.d("test","isi");
        }
        tvIsi.setText(dbMateri.findMateri().getIsi());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}