package com.example.qrtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayCheckedIn extends AppCompatActivity {

    String buildingID;
    public static final String EXTRA_MESSAGE = "com.example.qrtest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_checked_in);

        // get the intent that started this activity and extract the string
        Intent intent = getIntent();
        buildingID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // set building text to show the building that was scanned by the QR scanner
        TextView buildingText = findViewById(R.id.checkedInBuildingText);
        buildingText.setText(buildingID);
    }

    // function for hitting "check out button"
    public void returnToQRScanner(View view){

        // after verifying QR and retrieving building ID, go to DisplayQRConfirmation activity
        Intent intent = new Intent(this, MainActivity.class);
        String message = "goBack";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}