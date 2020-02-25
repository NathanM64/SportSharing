package com.example.sportsharing;

import android.app.Dialog;
import android.content.Context;
import android.widget.CalendarView;

public class DateSelectorActivity extends Dialog {

    private CalendarView calendar;
    private int annee, mois, jour;

    public DateSelectorActivity(Context context) {
        super(context, R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        setContentView(R.layout.date_selector);

        calendar = findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(dateChanged);

        show();
    }

    public int getAnnee() { return annee; }

    public int getMois() { return mois; }

    public int getJour() { return jour; }

    //Listener
    CalendarView.OnDateChangeListener dateChanged = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d) {
            annee = y;
            mois = m;
            jour = d;
        }
    };
}


