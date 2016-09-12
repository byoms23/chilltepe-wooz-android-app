package me.wooz.mobile.android.app.login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by byron on 11/09/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    protected OnDateSetListener mCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Start date
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.clear();
        startCalendar.set(Calendar.YEAR, 1900);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        DatePicker datePicker = pickerDialog.getDatePicker();
        datePicker.setMinDate(startCalendar.getTime().getTime());
        datePicker.setMaxDate(c.getTime().getTime());
        pickerDialog.setTitle("");

        // Return picker
        return pickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        if(mCallback != null) {
            mCallback.onDateSet(year, month, day);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnDateSetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnDateSetListener");
        }
    }

    // Container Activity must implement this interface
    public interface OnDateSetListener {
        public void onDateSet(int year, int month, int day);
    }

}

