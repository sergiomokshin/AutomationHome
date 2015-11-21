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


	private LinearLayout llSelecionaSaida;
	private LinearLayout llSetupDevice;
	
	private Button btS1;
	private String NameS1 = "";
	
	private Button btS2;
	private String NameS2 = "";
	
	private Button btS3;
	private String NameS3 = "";
	
	private Button btS4;
	private String NameS4 = "";
	
	private Button btS5;
	private String NameS5 = "";
	
	private Button btS6;
	private String NameS6 = "";
	
	private Button btS7;
	private String NameS7 = "";
	
	private Button btS8;
	private String NameS8 = "";
	
	
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
	private Button btCancelar;
	

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(final Bundle savedInstanceState) {

		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_device);
		
		llSelecionaSaida.setVisibility(LinearLayout.VISIBLE);
		llSetupDevice.setVisibility(LinearLayout.GONE); //INVISIBLE		
		
		btS1 = (Button) findViewById(R.id.btS1);
		NameS1 = sharedPreferences.getString("S1", "Saída 1");
		btS1.setText(NameS1);

		btS1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			  OpenSetup("1");
        });
		
		btS2 = (Button) findViewById(R.id.btS2);
		NameS2 = sharedPreferences.getString("S2", "Saída 2");
		btS2.setText(NameS2);

		btS2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			   OpenSetup("2");
            }
        });
		
		
		btS3 = (Button) findViewById(R.id.btS3);
		NameS3 = sharedPreferences.getString("S3", "Saída 3");
		btS3.setText(NameS3);

		btS3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			  OpenSetup("3");
            }
        });
		
		
		btS4 = (Button) findViewById(R.id.btS4);
		NameS4 = sharedPreferences.getString("S4", "Saída 4");
		btS4.setText(NameS4);

		btS4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    OpenSetup("4");
            }
        });
		
		btS5 = (Button) findViewById(R.id.btS5);
		NameS5 = sharedPreferences.getString("S5", "Saída 5");
		btS5.setText(NameS5);

		btS5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			   OpenSetup("5");
            }
        });
		
		
		btS6 = (Button) findViewById(R.id.btS6);
		NameS6 = sharedPreferences.getString("S6", "Saída 6");
		btS6.setText(NameS6);

		btS6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    OpenSetup("6");
            }
        });
		
		
		btS7 = (Button) findViewById(R.id.btS7);
		NameS7 = sharedPreferences.getString("S7", "Saída 7");
		btS7.setText(NameS7);

		btS7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    OpenSetup("7");
            }
        });
		
		
		btS8 = (Button) findViewById(R.id.btS8);
		NameS8 = sharedPreferences.getString("S8", "Saída 8");
		btS8.setText(NameS8);

		btS8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {               
			    OpenSetup("8");
            }
        });
				
		btAlterar = (Button) findViewById(R.id.btAlterar);				
		btCancelar = (Button) findViewById(R.id.btCancelar);	

		btCancelar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {               
				llSelecionaSaida.setVisibility(LinearLayout.VISIBLE); //INVISIBLE		
				llSetupDevice.setVisibility(LinearLayout.GONE); 
            }
        });		
        
    }
	
	private void OpenSetup(String saidaAlterada)
	{
		
		
		llAgendamento = (LinearLayout)findViewById(R.id.llAgendamento);					
		llPulso = (LinearLayout)findViewById(R.id.llPulso);
		
	    sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
		
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
	
		llSelecionaSaida.setVisibility(LinearLayout.GONE);//INVISIBLE		
		llSetupDevice.setVisibility(LinearLayout.VISIBLE); 
				
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
