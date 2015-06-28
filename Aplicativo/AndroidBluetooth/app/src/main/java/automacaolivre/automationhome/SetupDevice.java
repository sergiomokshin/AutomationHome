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
    private String NameS3 = "";
    private String NameS4 = "";
    private String NameS5 = "";
    private String NameS6 = "";
    private String NameS7 = "";
    private String NameS8 = "";
    private String NameSRGB = "";
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

        NameS2 = sharedPreferences.getString("S2", "S2");
        NameS3 = sharedPreferences.getString("S3", "S3");
        NameS4 = sharedPreferences.getString("S4", "S4");
        NameS5 = sharedPreferences.getString("S5", "S5");
        NameS6 = sharedPreferences.getString("S6", "S6");
        NameS7 = sharedPreferences.getString("S7", "S7");
        NameS8 = sharedPreferences.getString("S8", "S8");
        NameSRGB = sharedPreferences.getString("SRGB", "ILUMINACAO");

        btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();

                editor.putString("S1", edtS1.getText().toString());
                editor.putString("S1HrI", edtS1HI.getText().toString());
                editor.putString("S1HrF", edtS1HF.getText().toString());
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
