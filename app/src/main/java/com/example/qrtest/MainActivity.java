package com.example.qrtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.qrtest.MESSAGE";
    boolean isAtCapacity = false;
    private String scanText;
    private int buildingID;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // request permission to use camera
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }

    public void scanButton(View view){
        /** onclick command for scanning */
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /** when a QR code has been scanned, store the buildingID that was scanned */

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(intentResult != null){
            if(intentResult.getContents() == null){
                // error, could not scan code
                scanText = "Could not read QR code. Please try again.";
            }
            else{
                scanText = intentResult.getContents();
                TextView textView = findViewById(R.id.textView);
                textView.setText(scanText);
                buildingID = Integer.parseInt(scanText);
                verifyQR();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void verifyQR(){
        /** Based on building id, go to confirmation activity */

        // Create CheckInOut Model object
        CheckInOutModel model = new CheckInOutModel();

        // Convert buildingID to building name
        String buildingName = model.queryQRToBuilding(buildingID);

        // pop-up error if buildingID has no matches
        if(buildingName == null){
            TextView textView = findViewById(R.id.textView);
            textView.setText("ERROR: INVALID QR");
            return;
        }

        // verify if the user is not checked in
        if(model.userIsCheckedIn(userID)){
            TextView textView = findViewById(R.id.textView);
            textView.setText("ERROR: ALREADY CHECKED IN");
            return;
        }

        // check if building is full
        if(model.queryBuildingIsFull(buildingID)){
            Intent intent = new Intent(this, DisplayNoCapacity.class);
            intent.putExtra(EXTRA_MESSAGE, buildingName);
            startActivity(intent);
        }
        else{
            // if building is not full, go to confirmation activity
            Intent intent = new Intent(this, DisplayQRConfirmation.class);
            intent.putExtra(EXTRA_MESSAGE, buildingName);
            startActivity(intent);
        }

        // if the building is full
        if(isAtCapacity){
            Intent intent = new Intent(this, DisplayNoCapacity.class);
            String message = "KAP";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
        else{
            // after verifying QR and retrieving building ID, go to DisplayQRConfirmation activity
            Intent intent = new Intent(this, DisplayQRConfirmation.class);
            String message = "KAP";
            intent.putExtra(EXTRA_MESSAGE, scanText);
            startActivity(intent);
        }

    }

}