package com.example.qrtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayQRConfirmation extends AppCompatActivity {

    String buildingID;
    public static final String EXTRA_MESSAGE = "com.example.qrtest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_q_r_confirmation);

        // get the intent that started this activity and extract the string
        Intent intent = getIntent();
        buildingID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // set building text to show the building that was scanned by the QR scanner
        TextView buildingText = findViewById(R.id.buildingText);
        buildingText.setText(buildingID);

    }

    // function for hitting "go back button"
    public void returnToQRScanner(View view){

        // after verifying QR and retrieving building ID, go to DisplayQRConfirmation activity
        Intent intent = new Intent(this, MainActivity.class);
        String message = "goBack";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // function for hitting "proceed button"
    public void displayCheckedIn(View view){

        // after verifying QR and retrieving building ID, go to DisplayQRConfirmation activity
        Intent intent = new Intent(this, DisplayCheckedIn.class);
        String message = buildingID;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}