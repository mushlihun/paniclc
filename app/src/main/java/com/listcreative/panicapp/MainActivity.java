package com.listcreative.panicapp;

/**
 * Created by Mushlihun on 08/02/2018.
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 10000;
    private TextView amb, pol, header;
    private ProgressBar progressBar;

//    pol = (TextView) findViewById(R.id.pol);
//    amb = (TextView) findViewById(R.id.amb);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Help ListCreative");
        RelativeLayout btn_sos = (RelativeLayout)findViewById(R.id.btn_sos);
        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(false);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.popup_pilihan);
                dialog.show();

                pol = (TextView) dialog.findViewById(R.id.pol);
                amb = (TextView) dialog.findViewById(R.id.amb);
                header = (TextView) dialog.findViewById(R.id.header);
                progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                pol.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        header.setText("Call Police");
                        progressBar.setVisibility(View.VISIBLE);
                        amb.setVisibility(View.GONE);
                        pol.setVisibility(View.GONE);
                        setLoading(dialog, "Police");
                    }
                });
                amb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        header.setText("Call Ambulance");
                        progressBar.setVisibility(View.VISIBLE);
                        amb.setVisibility(View.GONE);
                        pol.setVisibility(View.GONE);
                        setLoading(dialog, "Ambulance");
                    }
                });
            }
        });
    }

    public void setLoading(final Dialog dialog, final String choice){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, choice+" on the way", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Map.class);
                i.putExtra("choice", choice);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);
    }
}

