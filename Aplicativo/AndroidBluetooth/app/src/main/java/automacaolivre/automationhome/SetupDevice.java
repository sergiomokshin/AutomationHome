//http://www.decom.ufop.br/imobilis/?p=3081
package automacaolivre.automationhome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;


import android.content.SharedPreferences;

public class SetupDevice extends Activity {

	private String NameSaida1 = "Saída 1";
    private String NameSaida2 = "Saída 2";
    private String NameSaida3 = "Saída 3";
    private String NameSaida4 = "Saída 4";
	private String NameSaida5 = "Saída 5";
	private String NameSaida6 = "Saída 6";
	private String NameSaida7 = "Saída 7";
	private String NameSaida8 = "Saída 8";
	private String NameSaidaRGB = "Iluminaçãoo";
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
		       
		NameSaida1 = ApplicationPreferences.getInstance().getNameSaida1();
		NameSaida2 = ApplicationPreferences.getInstance().getNameSaida2();
		NameSaida3 = ApplicationPreferences.getInstance().getNameSaida3();
		NameSaida4 = ApplicationPreferences.getInstance().getNameSaida4();
		NameSaida5 = ApplicationPreferences.getInstance().getNameSaida5();
		NameSaida6 = ApplicationPreferences.getInstance().getNameSaida6();
		NameSaida7 = ApplicationPreferences.getInstance().getNameSaida7();
		NameSaida8 = ApplicationPreferences.getInstance().getNameSaida8();
		NameSaidaRGB = ApplicationPreferences.getInstance().getNameSaidaRGB();
		
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

					ApplicationPreferences.getInstance().setNameSaida1(edtSaida1.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida2(edtSaida2.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida2(edtSaida2.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida3(edtSaida3.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida4(edtSaida4.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida5(edtSaida5.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida6(edtSaida6.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida7(edtSaida7.getText().toString());
					ApplicationPreferences.getInstance().setNameSaida8(edtSaida8.getText().toString());
					ApplicationPreferences.getInstance().setNameSaidaRGB(edtSaidaRGB.getText().toString());
                    																				
					setResult(Activity.RESULT_OK);
					finish();

            }
        });
    }
}