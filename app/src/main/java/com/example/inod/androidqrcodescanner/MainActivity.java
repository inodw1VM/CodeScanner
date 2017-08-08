package com.example.inod.androidqrcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button scan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the user interface layout for this Activity
            // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.activity_main);

        //Defined a Button in the layout file and assigned it a unique ID
            //Button scan_btn = (Button) findViewById(R.id.scan_btn);
        scan_btn = (Button) findViewById(R.id.scan_btn); //find the button

        final Activity activity = this;

        //Button, have an interface that listens to the button events
            // Set my button event to listen to the user events
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
                //What want to do after the event comes inside the onClick method
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES); /*QR_Code_Types for scan QR Code*/
                integrator.setPrompt("Scaning...");//show while scanning
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setTimeout(8000);
                integrator.initiateScan();
            }
        });

    }
        // after scanning code come back to current activity
        // first method that call is onActivityResult(int requestCode, int resultCode, Intent data)
        // get the result in this method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            //IntentResult - Encapsulates the result of a barcode scan invoked through IntentIntegrator.

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Scan is Canselled", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
