package com.example.sportsharing;

import android.app.Dialog;
import android.content.Context;
import android.widget.TimePicker;

public class TimeSelectorActivity extends Dialog {

    private TimePicker timer;
    private int heure, minute;

    public TimeSelectorActivity(Context context) {
        super(context, R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        setContentView(R.layout.time_selector);

        timer = findViewById(R.id.timer);

        timer.setOnTimeChangedListener(timerChange);

        show();
    }

    public TimePicker getTimer() { return timer; }

    public int getHeure() { return heure; }

    public int getMinute() { return minute; }

    //Listener
    TimePicker.OnTimeChangedListener timerChange = new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker timePicker, int h, int m) {
            heure = h;
            minute = m;
        }
    };
}


