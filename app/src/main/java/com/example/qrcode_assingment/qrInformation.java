package com.example.qrcode_assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * The type Qr information.
 */
public class qrInformation extends AppCompatActivity {
    private TextView info;
    /**
     * The Information.
     */
    public String information;
//    public static final String SHARED_PREFS = "prefs";
//    public static final String KEY_BUTTON_TEXT = "keyButtonText";
//    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_information);
        information = getIntent().getStringExtra("information");
        info=findViewById(R.id.information);
        info.setText(information);
//        Intent configIntent = getIntent();
//        Bundle extras = configIntent.getExtras();
//        if (extras != null) {
//            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                    AppWidgetManager.INVALID_APPWIDGET_ID);
//        }
//        Intent resultValue = new Intent();
//        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//        setResult(RESULT_CANCELED, resultValue);
//        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
//            finish();
//        }
    }

//    public void confirmConfiguration(View v) {
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        String buttonText=information;
//        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.widget);
//        views.setOnClickPendingIntent(R.id.widget_text, pendingIntent);
//        views.setCharSequence(R.id.widget_text, "setText", buttonText);
//        views.setTextViewText(R.id.widget_text,buttonText);
//        appWidgetManager.updateAppWidget(appWidgetId,views);
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(KEY_BUTTON_TEXT + "setter", buttonText);
//        editor.apply();
//        Intent resultValue = new Intent();
//        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//        setResult(RESULT_OK, resultValue);
//        finish();
//    }
}