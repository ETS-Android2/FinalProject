package com.example.hitchikersguide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import java.util.Date;

/** DatePicker activity contains a date picker to select
 * an image to view from that date
 * Passes selected date to ImageDisplay to view image
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class DatePicker extends BaseActivity {

    /** Add DatePicker layout to BaseActivity
     * Include date picker and Intent to pass
     * image date to ImageDisplay class
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the Date Picker layout into the Base activity frame
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_date_picker, contentFrameLayout);

        // Initialize Date Picker
        android.widget.DatePicker picker;
        picker = findViewById(R.id.DP_datePicker);

        // Set maximum date (minimum is set already)
        picker.setMaxDate(new Date().getTime());

        Button dateButton = findViewById(R.id.DP_PickDate);
        dateButton.setOnClickListener(click -> {

            String newDate = picker.getYear() + "-" + (picker.getMonth() + 1 )+ "-" + picker.getDayOfMonth();
            Log.i("DatePicker: ", "date selected is: " + newDate);

            // Open intent and pass date to Image Display activity
            Intent passDate = new Intent(getBaseContext(), ImageDisplay.class);
            passDate.putExtra("Date", newDate);
            startActivity(passDate);
        });
    }
}