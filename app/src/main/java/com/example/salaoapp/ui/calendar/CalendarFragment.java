package com.example.salaoapp.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.salaoapp.R;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private CalendarView calendar;
    private TextView myDate;

    public View onCreateView(View view,@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

         calendar = (CalendarView) getView().findViewById(R.id.calendarView);
         myDate = (TextView) getView().findViewById(R.id.myDate);

         calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                myDate.setText(i2+"/"+i1+"/"+i);
             }
         });
        return root;
    }
}