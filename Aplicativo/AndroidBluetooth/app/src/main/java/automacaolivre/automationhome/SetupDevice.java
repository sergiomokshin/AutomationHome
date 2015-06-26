
package automacaolivre.automationhome;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;


import android.content.SharedPreferences;

public class SetupDevice extends Activity {

    private String NameSaida1 = "";
    private String NameSaida2 = "";
    private String NameSaida3 = "";
    private String NameSaida4 = "";
    private String NameSaida5 = "";
    private String NameSaida6 = "";
    private String NameSaida7 = "";
    private String NameSaida8 = "";
    private String NameSaidaRGB = "";
    private Button btAlterar;

    private EditText edtSaida1;
    private EditText edtSaida2;
    private EditText edtSaida3;
    private EditText edtSaida4;
    private EditText edtSaida5;
    private EditText edtSaida6;
    private EditText edtSaida7;
    private EditText edtSaida8;
    private EditText edtSaidaRGB;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_device);

        btAlterar = (Button) findViewById(R.id.btAlterar);
        edtSaida1 = (EditText) findViewById(R.id.edtSaida1);
        edtSaida2 = (EditText) findViewById(R.id.edtSaida2);
        edtSaida3 = (EditText) findViewById(R.id.edtSaida3);
        edtSaida4 = (EditText) findViewById(R.id.edtSaida4);
        edtSaida5 = (EditText) findViewById(R.id.edtSaida5);
        edtSaida6 = (EditText) findViewById(R.id.edtSaida6);
        edtSaida7 = (EditText) findViewById(R.id.edtSaida7);
        edtSaida8 = (EditText) findViewById(R.id.edtSaida8);
        edtSaidaRGB = (EditText) findViewById(R.id.edtSaidaRGB);


        sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
        NameSaida1 = sharedPreferences.getString("SAIDA1", "Saída 1");
        NameSaida2 = sharedPreferences.getString("SAIDA2", "Saída 2");
        NameSaida3 = sharedPreferences.getString("SAIDA3", "Saída 3");
        NameSaida4 = sharedPreferences.getString("SAIDA4", "Saída 4");
        NameSaida5 = sharedPreferences.getString("SAIDA5", "Saída 5");
        NameSaida6 = sharedPreferences.getString("SAIDA6", "Saída 6");
        NameSaida7 = sharedPreferences.getString("SAIDA7", "Saída 7");
        NameSaida8 = sharedPreferences.getString("SAIDA8", "Saída 8");
        NameSaidaRGB = sharedPreferences.getString("SAIDARGB", "ILUMINACAO");

        edtSaida1.setText(NameSaida1);
        edtSaida2.setText(NameSaida2);
        edtSaida3.setText(NameSaida3);
        edtSaida4.setText(NameSaida4);
        edtSaida5.setText(NameSaida5);
        edtSaida6.setText(NameSaida6);
        edtSaida7.setText(NameSaida7);
        edtSaida8.setText(NameSaida8);
        edtSaidaRGB.setText(NameSaidaRGB);

        btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();

                editor.putString("SAIDA1", edtSaida1.getText().toString());
                editor.putString("SAIDA2", edtSaida2.getText().toString());
                editor.putString("SAIDA3", edtSaida3.getText().toString());
                editor.putString("SAIDA4", edtSaida4.getText().toString());
                editor.putString("SAIDA5", edtSaida5.getText().toString());
                editor.putString("SAIDA6", edtSaida6.getText().toString());
                editor.putString("SAIDA7", edtSaida7.getText().toString());
                editor.putString("SAIDA8", edtSaida8.getText().toString());
                editor.putString("SAIDARGB", edtSaidaRGB.getText().toString());

                editor.commit();
                setResult(Activity.RESULT_OK);
                finish();

            }
        });
    }
}