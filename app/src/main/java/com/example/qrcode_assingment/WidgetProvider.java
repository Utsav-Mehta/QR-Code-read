package com.example.qrcode_assingment;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * The type Widget provider.
 */
public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//            String buttonText = prefs.getString(KEY_BUTTON_TEXT +"setter", "Press me");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
//            views.setCharSequence(R.id.widget_text, "setText", buttonText);
//            views.setTextViewText(R.id.widget_text,buttonText);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
}

    @Override
    public void onReceive(Context context, Intent intent) {
//        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//        String buttonText = prefs.getString(KEY_BUTTON_TEXT +"setter", "Press me");
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
//        views.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
//        views.setCharSequence(R.id.widget_text, "setText", buttonText);
//        views.setTextViewText(R.id.widget_text,buttonText);
    }
}
