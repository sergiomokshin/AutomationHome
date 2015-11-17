package automacaolivre.automationhomeplus;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SetupDevice extends Activity  {

	private	TextView txtS;
	private EditText edtS;
	private Spinner spTipo;
	
	private String saidaAlterada;
	private String tipoSaida;
	
    private String NameS1 = "";
    private String S1HrI = "";
    private String S1HrF = "";
    
    private EditText edtSHI;
	private EditText edtSHF;
    private EditText edtSMI;
    private EditText edtSMF;
		
	private EditText edtSP;
	
	private LinearLayout llAgendamento;
	private LinearLayout llPulso;
		
    private Button btAlterar;
	

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_device);
		btAlterar = (Button) findViewById(R.id.btAlterar);
		llAgendamento = (LinearLayout)findViewById(R.id.llAgendamento);					
		llPulso = (LinearLayout)findViewById(R.id.llPulso);
			
        sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
		
		saidaAlterada = sharedPreferences.getString("SaidaSetup", "");
		String nomeSaida = sharedPreferences.getString("S" + saidaAlterada, "Saída" + saidaAlterada);
		String horaInicio = sharedPreferences.getString("S" + saidaAlterada + "HI" , "0");
		String horaFim = sharedPreferences.getString("S" + saidaAlterada + "HF" , "23");
		String minutoInicio = sharedPreferences.getString("S" + saidaAlterada + "MI" , "0");
		String minutoFim = sharedPreferences.getString("S" + saidaAlterada + "MF" , "59");
		String tempoPulso = sharedPreferences.getString("S" + saidaAlterada + "P" , "3");
		tipoSaida = sharedPreferences.getString("TS" + saidaAlterada , "M");
				
		txtS = (TextView) findViewById(R.id.txtS);		
		txtS.setText("Saída " + saidaAlterada);
		
		edtS = (EditText) findViewById(R.id.edtS);
		edtS.setText(nomeSaida);
					
		spTipo = (Spinner) findViewById(R.id.spTipo);
		String[] array_spinner = new String[3];
        array_spinner[0] = "Manual";
        array_spinner[1] = "Agendado";
        array_spinner[2] = "Pulso";        
		        
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
		spTipo.setAdapter(adapter);
					
		if(tipoSaida == "M"){		
			spTipo.setSelection(0);
		} else if(tipoSaida == "A"){				
			spTipo.setSelection(1);
			
		} else if(tipoSaida == "P"){				
			spTipo.setSelection(2);			
		}		
		else{
			spTipo.setSelection(0);
		}
				
		MostraTipo(tipoSaida);				
		
		
		edtSHI = (EditText) findViewById(R.id.edtSHI);
		edtSHI.setText(horaInicio);
		edtSHF = (EditText) findViewById(R.id.edtSHF);
		edtSHI.setText(horaFim);
		edtSMI = (EditText) findViewById(R.id.edtSMI);
		edtSHI.setText(minutoInicio);
		edtSMF = (EditText) findViewById(R.id.edtSMF);
		edtSHI.setText(minutoFim);
								
		edtSP = (EditText) findViewById(R.id.edtSP);
		edtSP.setText(tempoPulso);
								
		spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{				
				if(position == 0)
				{	
					tipoSaida = "M";
					
				} else if(position == 1)
				{				
					tipoSaida = "A";
					
				} else if(position == 2)
				{				
					tipoSaida = "P";				
				}									
				MostraTipo(tipoSaida);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
    	
        btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();
				
                editor.putString("S" + saidaAlterada, edtS.getText().toString());					
				editor.putString("S" + saidaAlterada + "HI" , edtSHI.getText().toString());
				editor.putString("S" + saidaAlterada + "HF" , edtSHF.getText().toString());
				editor.putString("S" + saidaAlterada + "MI" , edtSMI.getText().toString());
				editor.putString("S" + saidaAlterada + "MF" , edtSMF.getText().toString());
				editor.putString("S" + saidaAlterada + "P" , edtSP.getText().toString());
				editor.putString("TS" + saidaAlterada,  tipoSaida);
														
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
	
	private void MostraTipo(String tipo)
	{
		llAgendamento.setVisibility(View.GONE);
		llPulso.setVisibility(View.GONE);	
		
		if(tipoSaida == "M"){			
		
		} else if(tipoSaida == "A"){				
			llAgendamento.setVisibility(View.VISIBLE);		
		
		} else if(tipoSaida == "P"){				
			llPulso.setVisibility(View.VISIBLE);			
		}				
	}
	
}
