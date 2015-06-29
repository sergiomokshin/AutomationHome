package automacaolivre.automationhome;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.content.SharedPreferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefineHoraData extends Activity implements OnClickListener {

    Button btnCalendar, btnTimePicker, btAlterar;
    EditText txtDate, txtTime;

    private int mYear, mMonth, mDay, mHour, mMinute;
	private String Data, Hora;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
	
	private Pattern patternD;
	private Matcher matcherD;
	private static final String DATE_PATTERN =  " ((19|20)\\d\\d)  [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] (0?[1-9]|1[012]) ";
	//public DateValidator(){ patternD = Pattern.compile(DATE_PATTERN);
	
	
	private Pattern pattern;
	private Matcher matcher;	
	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";	
//	public Time24HoursValidator(){ pattern = Pattern.compile(TIME24HOURS_PATTERN);

    @Override
    public void onCreate(final Bundle savedInstanceState) {
	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.define_hora_data);

        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);
		btAlterar = (Button) findViewById(R.id.btAlterar);

        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
		
		sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
		Data = sharedPreferences.getString("Data", "");
		Hora = sharedPreferences.getString("Hora", "");
		
		txtDate.setText(Data);
		txtTime.setText(Hora);

        btnCalendar.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
				
		btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();
				
				Data = mDay + "/" + mMonth + "/" + mYear;
				Hora = mHour + ":" + mMinute;								
				

				//http://stackoverflow.com/questions/17416595/date-validation-in-android
				/*
				if(!validateDate(Data))
				{
					Toast.makeText(getApplicationContext(), "Data invalida, utilizar o formado DD/MM/YYYY!", Toast.LENGTH_SHORt).show();
					return;				
				}
								
				if(!validateTime(Hora))
				{
					Toast.makeText(getApplicationContext(), "Hora invalida, utilizar o formado HH:MM!", Toast.LENGTH_SHORt).show();
					return;				
				}
				*/
                editor.putString("Data", Data);
                editor.putString("Hora", Hora);
															
                editor.commit();

                Bundle conData = new Bundle();
                conData.putString("OK", "OK");
                Intent intent = new Intent();
                intent.putExtras(conData);
                setResult(3, intent);
                finish();
            }
        });		
    }
/*
	public boolean validateTime(final String time){
 
		  matcher = pattern.matcher(time);
		  return matcher.matches();
 
	  }	
	  
	public boolean validateDate(final String date){

	  matcherD = patternD.matcher(date);

	  if(matcherD.matches()){
		  matcherD.reset();

		  if(matcherD.find()){
			  String day = matcherD.group(1);
			  String month = matcherD.group(2);
			  int year = Integer.parseInt(matcherD.group(3));

			  if (day.equals("31") && 
				(month.equals("4") || month .equals("6") || month.equals("9") ||
					   month.equals("11") || month.equals("04") || month .equals("06") ||
					   month.equals("09"))) {
				 return false; // only 1,3,5,7,8,10,12 has 31 days
			  } 

			  else if (month.equals("2") || month.equals("02")) {
				   //leap year
				   if(year % 4==0){
					   if(day.equals("30") || day.equals("31")){
						   return false;
					   }
					   else{
						   return true;
					   }
				  }
				  else{
					  if(day.equals("29")||day.equals("30")||day.equals("31")){
						  return false;
					  }
					  else{
						  return true;
					  }
				  }
			  }

			  else{               
				  return true;                
			  }
		  }

		  else{
			   return false;
		  }        
	  }
	  else{
		  return false;
	  }              
	}
		  */
    @Override
    public void onClick(View v) {

        if (v == btnCalendar) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            txtDate.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if (v == btnTimePicker) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
    }
	

}
