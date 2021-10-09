package com.example.qrcode_assingment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrcode_assingment.Database.DatabaseHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private Button scanBtn,prevScans;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn=findViewById(R.id.scan);
        dialog = new Dialog(this);
        prevScans=findViewById(R.id.prevscans);
        dialog.setContentView(R.layout.custom_dialog);
        databaseHelper = new DatabaseHelper(this);

        prevScans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,previousQRs.class);
                startActivity(intent);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        MainActivity.this
                );
                intentIntegrator.setPrompt("Use volume up key for flash light");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );

        if(intentResult.getContents()!=null){
            AlertDialog.Builder builder= new AlertDialog.Builder(
                    MainActivity.this
            );
            String info=intentResult.toString();
            Intent intent= new Intent(MainActivity.this,qrInformation.class);
            intent.putExtra("information",info);
            Button Okay = dialog.findViewById(R.id.btn_okay);
            Button scanAgain = dialog.findViewById(R.id.scanagain);
            Button gotourl=dialog.findViewById(R.id.gotoUrl);
            TextView displaytext=dialog.findViewById(R.id.displayText2);

            Okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                    dialog.dismiss();
                    AddData(info);
                }
            });
            scanAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    IntentIntegrator intentIntegrator = new IntentIntegrator(
                            MainActivity.this
                    );
                    intentIntegrator.setPrompt("Use volume up key for flash light");
                    intentIntegrator.setBeepEnabled(true);
                    intentIntegrator.setOrientationLocked(true);

                    intentIntegrator.setCaptureActivity(Capture.class);
                    intentIntegrator.initiateScan();
                }
            });
            gotourl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   try {
                       Uri uri = Uri.parse(intentResult.getContents());
                       Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                       startActivity(intent);
                       AddData(info);
                   }
                   catch (Exception e){
                       Toast.makeText(MainActivity.this, "This QRcode is linked with no website", Toast.LENGTH_SHORT).show();

                   }

                }
            });
            displaytext.setText(intentResult.getContents());
//            builder.setTitle("Result");
//            builder.setMessage(intentResult.getContents());
//            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//
//            });
//            builder.setPositiveButton("Go to URL", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Uri uri = Uri.parse(intentResult.getContents());
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
//                }

//            });
//            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//
//            });
//            builder.show();
            dialog.show();
        }
        else{
            Toast.makeText(MainActivity.this, "Retry!", Toast.LENGTH_SHORT).show();
        }
    }
    public void AddData(String newEntry) {
        boolean insertData = databaseHelper.addData(newEntry);
    }
}