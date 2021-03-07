package com.example.qrtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DisplayNoCapacity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.qrtest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_no_capacity);
    }

    // function for hitting "go back button"
    public void returnToQRScanner(View view){

        // after verifying QR and retrieving building ID, go to DisplayQRConfirmation activity
        Intent intent = new Intent(this, MainActivity.class);
        String message = "goBack";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}