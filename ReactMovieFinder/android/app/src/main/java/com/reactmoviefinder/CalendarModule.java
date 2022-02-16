package com.reactmoviefinder;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Objects;

public class CalendarModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    CalendarModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location) {
        Log.d("CalendarModule", "Create event called with name: " + name
                + " and location: " + location);

        Toast.makeText(reactContext, "createCalendarEvent", Toast.LENGTH_SHORT).show();

    }
}
