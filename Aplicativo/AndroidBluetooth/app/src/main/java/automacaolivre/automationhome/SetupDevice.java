package automacaolivre.automationhome;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.widget.TextView;

public class SetupDevice extends Activity  {

    private String NameS1 = "";
    private String S1HrI = "";
    private String S1HrF = "";
    private EditText edtS1;
    private EditText edtS1HI;
    private EditText edtS1HF;
	
	private String NameS2 = "";
    private String S2HrI = "";
    private String S2HrF = "";
    private EditText edtS2;
    private EditText edtS2HI;
    private EditText edtS2HF;
	
	private String NameS3 = "";
    private String S3HrI = "";
    private String S3HrF = "";
    private EditText edtS3;
    private EditText edtS3HI;
    private EditText edtS3HF;
	
	private String NameS4 = "";
    private String S4HrI = "";
    private String S4HrF = "";
    private EditText edtS4;
    private EditText edtS4HI;
    private EditText edtS4HF;
	
	private String NameS5 = "";
    private String S5HrI = "";
    private String S5HrF = "";
    private EditText edtS5;
    private EditText edtS5HI;
    private EditText edtS5HF;
	
	private String NameS6 = "";
    private String S6HrI = "";
    private String S6HrF = "";
    private EditText edtS6;
    private EditText edtS6HI;
    private EditText edtS6HF;
	
	private String NameS7 = "";
    private String S7HrI = "";
    private String S7HrF = "";
    private EditText edtS7;
    private EditText edtS7HI;
    private EditText edtS7HF;
	
	private String NameS8 = "";
    private String S8HrI = "";
    private String S8HrF = "";
    private EditText edtS8;
    private EditText edtS8HI;
    private EditText edtS8HF;

	
	private String NameSRGB = "";
    private String SRGBHrI = "";
    private String SRGBHrF = "";
    private EditText edtSRGB;
   // private EditText edtSRGBHI;
    //private EditText edtSRGBHF;

    private Button btAlterar;


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_device);

        sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);

        btAlterar = (Button) findViewById(R.id.btAlterar);


        edtS1 = (EditText) findViewById(R.id.edtS1);
        edtS1HI = (EditText) findViewById(R.id.edtS1HI);
        edtS1HF = (EditText) findViewById(R.id.edtS1HF);
        NameS1 = sharedPreferences.getString("S1", "S1");
        S1HrI = sharedPreferences.getString("S1HrI", "0");
        S1HrF = sharedPreferences.getString("S1HrF", "23");
        edtS1.setText(NameS1);
        edtS1HI.setText(S1HrI, TextView.BufferType.EDITABLE);
        edtS1HF.setText(S1HrF, TextView.BufferType.EDITABLE);
		
		edtS2 = (EditText) findViewById(R.id.edtS2);
        edtS2HI = (EditText) findViewById(R.id.edtS2HI);
        edtS2HF = (EditText) findViewById(R.id.edtS2HF);
        NameS2 = sharedPreferences.getString("S2", "S2");
        S2HrI = sharedPreferences.getString("S2HrI", "0");
        S2HrF = sharedPreferences.getString("S2HrF", "23");
        edtS2.setText(NameS2);
        edtS2HI.setText(S2HrI, TextView.BufferType.EDITABLE);
        edtS2HF.setText(S2HrF, TextView.BufferType.EDITABLE);
		
		edtS3 = (EditText) findViewById(R.id.edtS3);
        edtS3HI = (EditText) findViewById(R.id.edtS3HI);
        edtS3HF = (EditText) findViewById(R.id.edtS3HF);
        NameS3 = sharedPreferences.getString("S3", "S3");
        S3HrI = sharedPreferences.getString("S3HrI", "0");
        S3HrF = sharedPreferences.getString("S3HrF", "23");
        edtS3.setText(NameS3);
        edtS3HI.setText(S3HrI, TextView.BufferType.EDITABLE);
        edtS3HF.setText(S3HrF, TextView.BufferType.EDITABLE);
		
		edtS4 = (EditText) findViewById(R.id.edtS4);
        edtS4HI = (EditText) findViewById(R.id.edtS4HI);
        edtS4HF = (EditText) findViewById(R.id.edtS4HF);
        NameS4 = sharedPreferences.getString("S4", "S4");
        S4HrI = sharedPreferences.getString("S4HrI", "0");
        S4HrF = sharedPreferences.getString("S4HrF", "23");
        edtS4.setText(NameS4);
        edtS4HI.setText(S4HrI, TextView.BufferType.EDITABLE);
        edtS4HF.setText(S4HrF, TextView.BufferType.EDITABLE);
		
		edtS5 = (EditText) findViewById(R.id.edtS5);
        edtS5HI = (EditText) findViewById(R.id.edtS5HI);
        edtS5HF = (EditText) findViewById(R.id.edtS5HF);
        NameS5 = sharedPreferences.getString("S5", "S5");
        S5HrI = sharedPreferences.getString("S5HrI", "0");
        S5HrF = sharedPreferences.getString("S5HrF", "23");
        edtS5.setText(NameS5);
        edtS5HI.setText(S5HrI, TextView.BufferType.EDITABLE);
        edtS5HF.setText(S5HrF, TextView.BufferType.EDITABLE);
		
		edtS6 = (EditText) findViewById(R.id.edtS6);
        edtS6HI = (EditText) findViewById(R.id.edtS6HI);
        edtS6HF = (EditText) findViewById(R.id.edtS6HF);
        NameS6 = sharedPreferences.getString("S6", "S6");
        S6HrI = sharedPreferences.getString("S6HrI", "0");
        S6HrF = sharedPreferences.getString("S6HrF", "23");
        edtS6.setText(NameS6);
        edtS6HI.setText(S6HrI, TextView.BufferType.EDITABLE);
        edtS6HF.setText(S6HrF, TextView.BufferType.EDITABLE);
		
		edtS7 = (EditText) findViewById(R.id.edtS7);
        edtS7HI = (EditText) findViewById(R.id.edtS7HI);
        edtS7HF = (EditText) findViewById(R.id.edtS7HF);
        NameS7 = sharedPreferences.getString("S7", "S7");
        S7HrI = sharedPreferences.getString("S7HrI", "0");
        S7HrF = sharedPreferences.getString("S7HrF", "23");
        edtS7.setText(NameS7);
        edtS7HI.setText(S7HrI, TextView.BufferType.EDITABLE);
        edtS7HF.setText(S7HrF, TextView.BufferType.EDITABLE);
		
		edtS8 = (EditText) findViewById(R.id.edtS8);
        edtS8HI = (EditText) findViewById(R.id.edtS8HI);
        edtS8HF = (EditText) findViewById(R.id.edtS8HF);
        NameS8 = sharedPreferences.getString("S8", "S8");
        S8HrI = sharedPreferences.getString("S8HrI", "0");
        S8HrF = sharedPreferences.getString("S8HrF", "23");
        edtS8.setText(NameS8);
        edtS8HI.setText(S8HrI, TextView.BufferType.EDITABLE);
        edtS8HF.setText(S8HrF, TextView.BufferType.EDITABLE);
		
		
		edtSRGB = (EditText) findViewById(R.id.edtSRGB);
       // edtSRGBHI = (EditText) findViewById(R.id.edtSRGBHI);
       // edtSRGBHF = (EditText) findViewById(R.id.edtSRGBHF);
        NameSRGB = sharedPreferences.getString("SRGB", "SRGB");
        SRGBHrI = sharedPreferences.getString("SRGBHrI", "0");
        SRGBHrF = sharedPreferences.getString("SRGBHrF", "23");
        edtSRGB.setText(NameSRGB);
        //edtSRGBHI.setText(SRGBHrI, TextView.BufferType.EDITABLE);
       // edtSRGBHF.setText(SRGBHrF, TextView.BufferType.EDITABLE);
		
		
        btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();

                editor.putString("S1", edtS1.getText().toString());
                editor.putString("S1HrI", edtS1HI.getText().toString());
                editor.putString("S1HrF", edtS1HF.getText().toString());
				
				editor.putString("S2", edtS2.getText().toString());
                editor.putString("S2HrI", edtS2HI.getText().toString());
                editor.putString("S2HrF", edtS2HF.getText().toString());
				
				editor.putString("S3", edtS3.getText().toString());
                editor.putString("S3HrI", edtS3HI.getText().toString());
                editor.putString("S3HrF", edtS3HF.getText().toString());
				
				editor.putString("S4", edtS4.getText().toString());
                editor.putString("S4HrI", edtS4HI.getText().toString());
                editor.putString("S4HrF", edtS4HF.getText().toString());
				
				editor.putString("S5", edtS5.getText().toString());
                editor.putString("S5HrI", edtS5HI.getText().toString());
                editor.putString("S5HrF", edtS5HF.getText().toString());
				
				editor.putString("S6", edtS6.getText().toString());
                editor.putString("S6HrI", edtS6HI.getText().toString());
                editor.putString("S6HrF", edtS6HF.getText().toString());
				
				editor.putString("S7", edtS7.getText().toString());
                editor.putString("S7HrI", edtS7HI.getText().toString());
                editor.putString("S7HrF", edtS7HF.getText().toString());
				
				editor.putString("S8", edtS8.getText().toString());
                editor.putString("S8HrI", edtS8HI.getText().toString());
                editor.putString("S8HrF", edtS8HF.getText().toString());
				
				editor.putString("SRGB", edtSRGB.getText().toString());
               // editor.putString("SRGBHrI", edtSRGBHI.getText().toString());
               // editor.putString("SRGBHrF", edtSRGBHF.getText().toString());
				
											
                editor.commit();

                Bundle conData = new Bundle();
                conData.putString("OK", "OK");
                Intent intent = new Intent();
                intent.putExtras(conData);
                setResult(2, intent);
                finish();

            }
        });
    }


}
