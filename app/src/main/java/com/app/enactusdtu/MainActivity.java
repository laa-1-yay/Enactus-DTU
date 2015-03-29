package com.app.enactusdtu;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private TextView displayTime;
    private LinearLayout pickTime;

    private int pHour;
    private int pMinute;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 1;


    SharedPreferences user;

    String username = null ;
    String useremailid = null ;
    String userdob = null ;
    String usermobile = null ;



    Dialog inviteoption;

    Button invitepoolfriends;

    EditText poolname;
    EditText organizerphonenumber;
    EditText totalamount;
    LinearLayout targetdate;
    EditText valuetargetdate;
    EditText description;

    private int pYear;
    private int pMonth;
    private int pDay;

    Button phonecontacts;
    Button emailcontacts;

    static final int DATE_DIALOG_ID = 0;

    String organizerphonetext =null;
    int totalamounttext=0;
    String poolnametext =null;

    String returnorganizerphonetext=  null ;
    String returntotalamounttext ="0";
    String returnpoolnametext = null;
    String returndate = null;
    String returndescription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        SharedPreferences user = getSharedPreferences("USER", 0);

        username = user.getString("username",null);
        useremailid = user.getString("useremailid",null);
        userdob = user.getString("userdob",null);
        usermobile = user.getString("usermobile",null);



        invitepoolfriends = (Button) findViewById(R.id.invitefriends);

        invitepoolfriends.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        poolname = (EditText) findViewById(R.id.poolname);
        organizerphonenumber = (EditText) findViewById(R.id.phoneorganizer);
        totalamount = (EditText) findViewById(R.id.totalamount);
        valuetargetdate = (EditText) findViewById(R.id.valuedate);
        description = (EditText) findViewById(R.id.description);

        organizerphonenumber.setText(usermobile);

        targetdate = (LinearLayout) findViewById(R.id.targetdate);

        totalamount.setClickable(false);

        valuetargetdate.setClickable(false);
        valuetargetdate.setKeyListener(null);

        final Drawable originalDrawable = poolname.getBackground();

        poolname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                if(poolname.isFocused()) {
                    poolname.setBackground(originalDrawable);
                }
            }
        });

//        organizerphonenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, boolean arg1) {
//                if(organizerphonenumber.isFocused()) {
//                    organizerphonenumber.setBackground(originalDrawable);
//                }
//            }
//        });

//        totalamount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, boolean arg1) {
//                if(totalamount.isFocused()) {
//                    totalamount.setBackground(originalDrawable);
//                }
//            }
//        });


        targetdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                valuetargetdate.setBackground(originalDrawable);

            }
        });

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        /** Display the current date in the TextView */
//        updateDisplay();


        invitepoolfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returnpoolnametext = poolname.getText().toString();
                returndate = valuetargetdate.getText().toString();
                returnorganizerphonetext = organizerphonenumber.getText().toString();
                returndescription = description.getText().toString();

                int returntotalamounttextvalue=0;
                returntotalamounttext = totalamount.getText().toString();
                if(returntotalamounttext!=null) {
                    try{
                        returntotalamounttextvalue = Integer.valueOf(returntotalamounttext);
                    }catch (Exception e){
                    }
                }

                if (returnorganizerphonetext.length() == 0 || (returnorganizerphonetext.length() > 0 && returnorganizerphonetext.length() < 10) || returndate.length() == 0 ||
                        (returnorganizerphonetext.charAt(0) == '6') || (returnorganizerphonetext.charAt(0) == '5') || (returnorganizerphonetext.charAt(0) == '4') || (returnorganizerphonetext.charAt(0) == '3') ||
                        (returnorganizerphonetext.charAt(0) == '2') || (returnorganizerphonetext.charAt(0) == '1') ||
                        returntotalamounttext.length() <3 || (returntotalamounttextvalue < 100) || returnpoolnametext.length() == 0) {
//                    Toast.makeText(getApplicationContext(), "Please fill in all the details before you proceed !", Toast.LENGTH_SHORT).show();

                    if (returnorganizerphonetext.length() == 0) {
                        organizerphonenumber.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Empty phone number", Toast.LENGTH_SHORT).show();
                    }
//                    if ( returnorganizerphonetext.length() == 0 || (returnorganizerphonetext.length() > 0 && returnorganizerphonetext.length() < 10)) {
//                        organizerphonenumber.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Invalid number : Should be of 10 digits", Toast.LENGTH_SHORT).show();
//                    }
//                    if ( (returnorganizerphonetext.charAt(0) == '6') || (returnorganizerphonetext.charAt(0) == '5') || (returnorganizerphonetext.charAt(0) == '4') || (returnorganizerphonetext.charAt(0) == '3') || (returnorganizerphonetext.charAt(0) == '2') || (returnorganizerphonetext.charAt(0) == '1')) {
//                        organizerphonenumber.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Invalid number : Number should start with 9 or 8 or 7 ", Toast.LENGTH_SHORT).show();
//                    }

//                    if (returntotalamounttext.length()==0) {
//                        totalamount.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Minimum amount should be Rs 100", Toast.LENGTH_SHORT).show();
//                    }
//                    if (returntotalamounttextvalue < 100) {
//                        totalamount.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Minimum amount should be Rs 100", Toast.LENGTH_SHORT).show();
//                    }
                    if (returnpoolnametext.length() == 0) {
                        poolname.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter a pool name", Toast.LENGTH_SHORT).show();
                    }
                    if (returndate.length() == 0) {
                        valuetargetdate.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter a valid date", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Data added !", Toast.LENGTH_LONG).show();

                }
            }
        });




        /** Capture our View elements */
        pickTime = (LinearLayout) findViewById(R.id.picktime);

        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        /** Get the current time */
        final Calendar cal2 = Calendar.getInstance();
        pHour = cal2.get(Calendar.HOUR_OF_DAY);
        pMinute = cal2.get(Calendar.MINUTE);

        /** Display the current time in the TextView */
        updateDisplay2();
    }

    /** Create a new dialog for time picker */





    /**
     * Create a new dialog for date picker
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog d = new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);

                return d;

            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }


    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay2();
                    displayToast();
                }
            };

    /** Updates the time in the TextView */
    private void updateDisplay2() {
        totalamount.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
    }

    /** Displays a notification when the time is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(displayTime.getText()),   Toast.LENGTH_SHORT).show();

    }

    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    //  displayToast();
                }
            };

    /**
     * Updates the date in the TextView
     */
    private void updateDisplay() {
        valuetargetdate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(String.format("%02d", (pYear))).append("-")
                        .append(String.format("%02d", (pMonth + 1))).append("-")
                        .append(String.format("%02d", pDay)).append("")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
