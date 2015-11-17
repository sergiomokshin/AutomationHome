package automacaolivre.automationhomeplus;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.UUID;

import android.location.Address;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.widget.Button;
import android.widget.SeekBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.os.Handler;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class MainActivity extends Activity {


	private String Data = "";
	private String Hora = "";    
	private TextView txtHorario;
	
	
	private int S1 = 0;      //Status de acionamento da Saida S1	
	private int S1P = 0;      //Tempo de acionamento do pulso da Saida 1
	private int S1HI = 0;    //Hora inicio  ou  Tempo do pulso em segundos Saida 1 
	private int S1MI = 0;    //Minuto inicio Saida 1 
	private int S1HF = 0;    //Hora fim Saida 1 
	private int S1MF = 0;    //Minuto fim Saida 1 
	private String TS1 = "M";  // Conteúdo com TIPO DE ACIONAMENTO S1 (A-Agendado, M- Manual, P-Pulso )
	private Switch swS1;
	private TextView txtS1;
    private TextView txtTS1;
	private TextView txtHS1;
	private ImageButton imgS1;
	private String NameS1 = "";
	
	
	private int S2 = 0; 
	private int S2P = 0;
	private int S2HI = 0; 
	private int S2MI = 0; 
	private int S2HF = 0; 
	private int S2MF = 0; 
	private String TS2 = "M";
	private Switch swS2;
	private TextView txtS2;
    private TextView txtTS2;
	private TextView txtHS2;
	private ImageButton imgS2;
	private String NameS2 = "";
	
	private int S3 = 0; 
	private int S3P = 0;
	private int S3HI = 0; 
	private int S3MI = 0; 
	private int S3HF = 0; 
	private int S3MF = 0; 
	private String TS3 = "M";
	private Switch swS3;
	private TextView txtS3;
    private TextView txtTS3;
	private TextView txtHS3;
	private ImageButton imgS3;
	private String NameS3 = "";
	
	private int S4 = 0; 
	private int S4P = 0;
	private int S4HI = 0; 
	private int S4MI = 0; 
	private int S4HF = 0; 
	private int S4MF = 0; 
	private String TS4 = "M";
	private Switch swS4;
	private TextView txtS4;
    private TextView txtTS4;
	private TextView txtHS4;
	private ImageButton imgS4;
	private String NameS4 = "";
	
	private int S5 = 0; 
	private int S5P = 0;
	private int S5HI = 0; 
	private int S5MI = 0; 
	private int S5HF = 0; 
	private int S5MF = 0; 
	private String TS5 = "M";
	private Switch swS5;
	private TextView txtS5;
    private TextView txtTS5;
	private TextView txtHS5;
	private ImageButton imgS5;
	private String NameS5 = "";
	
	private int S6 = 0; 
	private int S6P = 0;
	private int S6HI = 0; 
	private int S6MI = 0; 
	private int S6HF = 0; 
	private int S6MF = 0; 
	private String TS6 = "M";
	private Switch swS6;
	private TextView txtS6;
    private TextView txtTS6;
	private TextView txtHS6;
	private ImageButton imgS6;
	private String NameS6 = "";
		
	private int S7 = 0; 
	private int S7P = 0;
	private int S7HI = 0; 
	private int S7MI = 0; 
	private int S7HF = 0; 
	private int S7MF = 0; 
	private String TS7 = "M"; 
	private Switch swS7;
	private TextView txtS7;
    private TextView txtTS7;
	private TextView txtHS7;
	private ImageButton imgS7;
	private String NameS7 = "";
	
	private int S8 = 0; 
	private int S8P = 0;
	private int S8HI = 0; 
	private int S8MI = 0; 
	private int S8HF = 0; 
	private int S8MF = 0; 
	private String TS8 = "M"; 
	private Switch swS8;
	private TextView txtS8;
    private TextView txtTS8;
	private TextView txtHS8;
	private ImageButton imgS8;
	private String NameS8 = "";
	
	private int SRGB;	
    private String NameSRGB = "";	
    private TextView txtSRGB;   
		
    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;
    private int SRed;
    private int SGreen;
    private int SBlue;

    String address;
    Handler handler = new Handler();
    private Boolean FirstTime;
    private boolean stopWorker = false;
    private int readBufferPosition = 0;
    private byte[] readBuffer = new byte[1024];
    private TextView txtMsg;
    byte delimiter = 10;

    private final int REQUEST_CONNECT_DEVICE = 1;
    private final int REQUEST_SETUP_DEVICE = 2;
	private final int REQUEST_SETUP_DATETIME = 3;

    private BluetoothAdapter meuAdaptadorBluetooth = null;
    private BluetoothSocket mmSocket = null;
    private BluetoothDevice mmDevice = null;
    private InputStream mmInStream = null;
    private OutputStream mmOutStream = null;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
			
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		Toast.makeText(this, "Conectando com o dispositivo.", Toast.LENGTH_LONG).show();		
		txtHorario = (TextView)findViewById(R.id.txtHorario);
		
		swS1 = (Switch)findViewById(R.id.swS1);
		txtS1 = (TextView)findViewById(R.id.txtS1);
		txtTS1 = (TextView) findViewById(R.id.txtTS1);
        txtHS1 = (TextView) findViewById(R.id.txtHS1);			
        imgS1 = (ImageButton) findViewById(R.id.btnCfgS1);
		
		swS2 = (Switch)findViewById(R.id.swS2);
		txtS2 = (TextView)findViewById(R.id.txtS2);
		txtTS2 = (TextView) findViewById(R.id.txtTS2);
        txtHS2 = (TextView) findViewById(R.id.txtHS2);			
        imgS2 = (ImageButton) findViewById(R.id.btnCfgS2);
        
		swS3 = (Switch)findViewById(R.id.swS3);
		txtS3 = (TextView)findViewById(R.id.txtS3);
		txtTS3 = (TextView) findViewById(R.id.txtTS3);
        txtHS3 = (TextView) findViewById(R.id.txtHS3);			
        imgS3 = (ImageButton) findViewById(R.id.btnCfgS3);
		
		swS4 = (Switch)findViewById(R.id.swS4);
		txtS4 = (TextView)findViewById(R.id.txtS4);
		txtTS4 = (TextView) findViewById(R.id.txtTS4);
        txtHS4 = (TextView) findViewById(R.id.txtHS4);			
        imgS4 = (ImageButton) findViewById(R.id.btnCfgS4);
		
		swS5 = (Switch)findViewById(R.id.swS5);
		txtS5 = (TextView)findViewById(R.id.txtS5);
		txtTS5 = (TextView) findViewById(R.id.txtTS5);
        txtHS5 = (TextView) findViewById(R.id.txtHS5);			
        imgS5 = (ImageButton) findViewById(R.id.btnCfgS5);
		
		swS6 = (Switch)findViewById(R.id.swS6);
		txtS6 = (TextView)findViewById(R.id.txtS6);
		txtTS6 = (TextView) findViewById(R.id.txtTS6);
        txtHS6 = (TextView) findViewById(R.id.txtHS6);			
        imgS6 = (ImageButton) findViewById(R.id.btnCfgS6);
				
		swS7 = (Switch)findViewById(R.id.swS7);
		txtS7 = (TextView)findViewById(R.id.txtS7);
		txtTS7 = (TextView) findViewById(R.id.txtTS7);
        txtHS7 = (TextView) findViewById(R.id.txtHS7);			
        imgS7 = (ImageButton) findViewById(R.id.btnCfgS7);
		
		swS8 = (Switch)findViewById(R.id.swS8);
		txtS8 = (TextView)findViewById(R.id.txtS8);
		txtTS8 = (TextView) findViewById(R.id.txtTS8);
        txtHS8 = (TextView) findViewById(R.id.txtHS8);			
        imgS8 = (ImageButton) findViewById(R.id.btnCfgS8);
		
		txtSRGB = (TextView) findViewById(R.id.txtSRGB);
		
        seekBarR = (SeekBar) findViewById(R.id.seekR);
        seekBarG = (SeekBar) findViewById(R.id.seekG);
        seekBarB = (SeekBar) findViewById(R.id.seekB);

        AtualizaLabels();

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        FirstTime = true;
		
		 swS1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("5");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("6");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("7");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 swS8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
			
		imgS1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "1");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });

		imgS2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "2");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });

		imgS3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "3");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });		

		imgS4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "4");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });      

		
		imgS5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "5");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });    

		imgS6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "6");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });  

		imgS7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "7");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });      
		
		imgS8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               
			    sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("SaidaSetup", "8");				
				editor.commit();
							
				Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.SetupDevice");
                startActivityForResult(intentS,90);
			   			   			  
            }
        });      
						
        seekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {         
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                try {
                    EnviarRGB("6", progress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                try {
                    EnviarRGB("5", progress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                try {
                    EnviarRGB("3", progress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        meuAdaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();
        address = sharedPreferences.getString("ADDRESS", "");
        
        if (address == "") {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.DeviceListActivity");
            startActivityForResult(intent,90);

        } else {
            Connect(false);
        }	
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    @Override
     public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DefineHoraData:
			
				sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("Data", Data);
				editor.putString("Hora", Hora);
				editor.commit();
							
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.DefineHoraData");
                startActivityForResult(intent,90);
                return true;
            case R.id.DeviceListActivity:
                Intent intentD = new Intent(Intent.ACTION_VIEW);
                intentD.setClassName("automacaolivre.automationhomeplus", "automacaolivre.automationhomeplus.DeviceListActivity");
                startActivityForResult(intentD,90);
                return true;            
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	private void AtualizaLabels() {

        sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        NameS1 = sharedPreferences.getString("S1", "Saída 1");
        NameS2 = sharedPreferences.getString("S2", "Saída 2");
        NameS3 = sharedPreferences.getString("S3", "Saída 3");
        NameS4 = sharedPreferences.getString("S4", "Saída 4");
        NameS5 = sharedPreferences.getString("S5", "Saída 5");
        NameS6 = sharedPreferences.getString("S6", "Saída 6");
        NameS7 = sharedPreferences.getString("S7", "Saída 7");
        NameS8 = sharedPreferences.getString("S8", "Saída 8");
        NameSRGB = sharedPreferences.getString("SRGB", "Iluminação");

        txtS1.setText(NameS1);
		txtS2.setText(NameS2);
		txtS3.setText(NameS3);
		txtS4.setText(NameS4);
		txtS5.setText(NameS5);
		txtS6.setText(NameS6);
		txtS7.setText(NameS7);
		txtS8.setText(NameS8);
		txtSRGB.setText(NameSRGB);		       
    }
	
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case REQUEST_CONNECT_DEVICE:

                    String addressAtual = sharedPreferences.getString("ADDRESS", "");
                    address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("ADDRESS", address);
                    editor.commit();

                    Boolean alterouDispositivo = (addressAtual.toString() != address.toString());
                    Connect(alterouDispositivo);
					
                break;
            case REQUEST_SETUP_DEVICE:
                try {
                    EnviaSetupPlaca();
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Erro durante a atualização dos parametros na placa, tente novamente!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                AtualizaLabels();
                break;
			case REQUEST_SETUP_DATETIME:
                AtualizaHorarioPlaca();
                break;								
        }
    }
	private void AtualizaHorarioPlaca() {
	
		String data = sharedPreferences.getString("Data", "");
		String hora = sharedPreferences.getString("Hora", "");
        try {
            if(writeData("|Ty" + data + "yz"  + hora + "z|")) {
                Toast.makeText(getApplicationContext(), "Data alterada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void EnviaSetupPlaca() throws InterruptedException {

        try {

            sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);			
			String saidaAlterada = sharedPreferences.getString("SaidaSetup", "");
			
            String horaInicio = sharedPreferences.getString("S" + saidaAlterada + "HI" , "");
			String horaFim = sharedPreferences.getString("S" + saidaAlterada + "HF" , "");
			String minutoInicio = sharedPreferences.getString("S" + saidaAlterada + "MI" , "");
			String minutoFim = sharedPreferences.getString("S" + saidaAlterada + "MI" , "");
			String tempoPulso = sharedPreferences.getString("S" + saidaAlterada + "P" , "");
			String tipoSaida = sharedPreferences.getString("TS" + saidaAlterada , "");
            
			String comandoHoraInicio = "|H" + saidaAlterada + "I" + String.format("%02d", Integer.parseInt(horaInicio)) + "|";
			String comandoHoraFim = "|H" + saidaAlterada + "F" + String.format("%02d", Integer.parseInt(horaFim)) + "|";
			String comandoMinutoInicio = "|M" + saidaAlterada + "I" + String.format("%02d", Integer.parseInt(minutoInicio)) + "|";
			String comandoMinutoFim = "|M" + saidaAlterada + "F" + String.format("%02d", Integer.parseInt(minutoFim)) + "|";
			String comandoTempoPulso = "|B" + saidaAlterada + String.format("%02d", Integer.parseInt(tempoPulso)) + "|";			
			String comandoTipoSaida = "|T" + saidaAlterada + tipoSaida + "|";
		
            if(writeData(comandoHoraInicio)) {						
                Thread.sleep(100);
                writeData(comandoHoraFim);
                Thread.sleep(100);
				writeData(comandoMinutoInicio);
                Thread.sleep(100);
				writeData(comandoMinutoFim);
                Thread.sleep(100);                                	
				writeData(comandoTempoPulso);
                Thread.sleep(100);

				if(writeData(comandoTipoSaida)) {
                    Toast.makeText(getApplicationContext(), "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Erro durante a atualização dos parametros na placa, tente novamente!", Toast.LENGTH_SHORT).show();
                }               
            }


        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erro durante a atualização dos parametros na placa, tente novamente!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    private void Connect(Boolean forceConect) {

        if(!forceConect && mmSocket != null && mmSocket.isConnected())
        {
            return;
        }

        meuAdaptadorBluetooth.cancelDiscovery();
        mmDevice = meuAdaptadorBluetooth.getRemoteDevice(address);

        try {

            mmSocket = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
            if(!mmSocket.isConnected())
            mmSocket.connect();
            mmInStream = mmSocket.getInputStream();
            mmOutStream = mmSocket.getOutputStream();

            String enviada = "";
            byte[] send = enviada.getBytes();
            mmOutStream.write(send);

            beginListenForCommands();

        } catch (IOException e) {
            Toast.makeText(this, "Ocorreu um erro durante a conexão com o Bluetooth, verifique se a placa de automação está ligada e dentro do raio de alcance!", Toast.LENGTH_LONG).show();
        }
    }

    private void EnviarRGB(String Porta, int Progress) throws IOException {

        String comando = "|A" + Porta + String.valueOf(Progress) + "|";
        writeData(comando);

    }

    private void EnviarComandoDigital(String S) throws IOException {

        //|D21| 
        String comando = "|";
        String StatusS = "";

        if (S == "1") {			
			if(TS1 == "A" )
			{
				Toast.makeText(getApplicationContext(), "Modo agendado não permite acionamento de comandos, altere configuração da saída " + NameS1 + " para o modo Manual!", Toast.LENGTH_SHORT).show();
				return;
				
			} else if(TS1 == "M" )
			{
				comando += "D" + S;
				StatusS = (S1 == 1) ? "0" : "1";
				comando += StatusS;	
				
			} else if(TS1 == "P" )
			{
				comando += "P" + S;
			}						            				
        }
				
        //int duracao = Toast.LENGTH_SHORT;
        //Toast toast = Toast.makeText(getApplicationContext(), comando, duracao);
        //toast.show();
		
		comando += "|";
        writeData(comando);
    }

    private boolean writeData(String data) throws IOException {

        try {
            return write(data);
        } catch (IOException ex) {
            meuAdaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();
            Connect(true);
            return write(data);
        }
    }

    private boolean write(String data) throws IOException {

        byte[] msgBuffer = data.getBytes();

        if(mmSocket.isConnected()) {

            mmOutStream.write(msgBuffer, 0, msgBuffer.length);
            return true;
        }
        else {
            Toast.makeText(this, "Dispositivo não conectado, reconecte e tente novamente!", Toast.LENGTH_LONG).show();
            return false;

        }

    }

    public void beginListenForCommands() {

        try {
            mmInStream = mmSocket.getInputStream();
        } catch (IOException e) {

            Toast.makeText(this, "Dispositivo não conectado, reconecte e tente novamente!", Toast.LENGTH_LONG).show();
        }

        Thread workerThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                    try {
                        int bytesAvailable = mmInStream.available();
                        if (bytesAvailable > 0) {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInStream.read(packetBytes);
                            for (int i = 0; i < bytesAvailable; i++) {
                                byte b = packetBytes[i];
                                if (b == delimiter) {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;
                                    handler.post(new Runnable() {
                                        public void run() {

                                            if (data.contains("dataCB")) {
                                                AtualizaDadosPlaca(data);
                                            }

                                            //txtMsg.setText(data);
                                            txtMsg.setText("");
                                        }
                                    });
                                } else {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    } catch (IOException ex) {
                        stopWorker = true;
                        //Toast.makeText(this, "Ocorreu um erro durante o recebimendo dos dados do dispositivo!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        workerThread.start();
    }

    private void AtualizaDadosPlaca(String data) {

        try {
			
					
            data = data.replace("dataCB(", "");
            data = data.replace(")", "");

            JSONObject jsonObj = new JSONObject( URLDecoder.decode(data, "UTF-8") );
		
			S1 = Integer.parseInt(jsonObj.getString("S1"));
			S1P = Integer.parseInt(jsonObj.getString("S1P"));
			S1HI = Integer.parseInt(jsonObj.getString("S1HI"));
			S1MI = Integer.parseInt(jsonObj.getString("S1MI"));
			S1HF = Integer.parseInt(jsonObj.getString("S1HF"));
			S1MF = Integer.parseInt(jsonObj.getString("S1MF"));
			TS1 = jsonObj.getString("TS1");		
			swS1.setChecked((S1 == 1));
			txtS1.setText(NameS1);
			if(TS1 == "A" )
			{
				txtTS1.setText("Agendado");		
				txtHS1.setText(S1HI + ":" + S1MI + " até " + S1HF + ":" + S1MF);
				
			} else if(TS1 == "M" )
			{
				txtTS1.setText("Manual");					
				txtHS1.setText("");
			}
			else if(TS1 == "P" )
			{
				txtTS1.setText("Pulso");					
				txtHS1.setText(S1P + " segundo(s)");
			}
			
			
			S2 = Integer.parseInt(jsonObj.getString("S2"));
			S2P = Integer.parseInt(jsonObj.getString("S2P"));
			S2HI = Integer.parseInt(jsonObj.getString("S2HI"));
			S2MI = Integer.parseInt(jsonObj.getString("S2MI"));
			S2HF = Integer.parseInt(jsonObj.getString("S2HF"));
			S2MF = Integer.parseInt(jsonObj.getString("S2MF"));
			TS2 = jsonObj.getString("TS2");		
			swS2.setChecked((S2 == 1));
			txtS2.setText(NameS2);
			if(TS2 == "A" )
			{
				txtTS2.setText("Agendado");		
				txtHS2.setText(S2HI + ":" + S2MI + " até " + S2HF + ":" + S2MF);
				
			} else if(TS2 == "M" )
			{
				txtTS2.setText("Manual");					
				txtHS2.setText("");
			}
			else if(TS2 == "P" )
			{
				txtTS2.setText("Pulso");					
				txtHS2.setText(S2P + " segundo(s)");
			}
			
			S3 = Integer.parseInt(jsonObj.getString("S3"));
			S3P = Integer.parseInt(jsonObj.getString("S3P"));
			S3HI = Integer.parseInt(jsonObj.getString("S3HI"));
			S3MI = Integer.parseInt(jsonObj.getString("S3MI"));
			S3HF = Integer.parseInt(jsonObj.getString("S3HF"));
			S3MF = Integer.parseInt(jsonObj.getString("S3MF"));
			TS3 = jsonObj.getString("TS3");		
			swS3.setChecked((S3 == 1));
			txtS3.setText(NameS3);
			if(TS3 == "A" )
			{
				txtTS3.setText("Agendado");		
				txtHS3.setText(S3HI + ":" + S3MI + " até " + S3HF + ":" + S3MF);
				
			} else if(TS3 == "M" )
			{
				txtTS3.setText("Manual");					
				txtHS3.setText("");
			}
			else if(TS3 == "P" )
			{
				txtTS3.setText("Pulso");					
				txtHS3.setText(S3P + " segundo(s)");
			}
			
            S4 = Integer.parseInt(jsonObj.getString("S4"));
			S4P = Integer.parseInt(jsonObj.getString("S4P"));
			S4HI = Integer.parseInt(jsonObj.getString("S4HI"));
			S4MI = Integer.parseInt(jsonObj.getString("S4MI"));
			S4HF = Integer.parseInt(jsonObj.getString("S4HF"));
			S4MF = Integer.parseInt(jsonObj.getString("S4MF"));
			TS4 = jsonObj.getString("TS4");		
			swS4.setChecked((S4 == 1));
			txtS4.setText(NameS4);
			if(TS4 == "A" )
			{
				txtTS4.setText("Agendado");		
				txtHS4.setText(S4HI + ":" + S4MI + " até " + S4HF + ":" + S4MF);
				
			} else if(TS4 == "M" )
			{
				txtTS4.setText("Manual");					
				txtHS4.setText("");
			}
			else if(TS4 == "P" )
			{
				txtTS4.setText("Pulso");					
				txtHS4.setText(S4P + " segundo(s)");
			}
			
			
			S5 = Integer.parseInt(jsonObj.getString("S5"));
			S5P = Integer.parseInt(jsonObj.getString("S5P"));
			S5HI = Integer.parseInt(jsonObj.getString("S5HI"));
			S5MI = Integer.parseInt(jsonObj.getString("S5MI"));
			S5HF = Integer.parseInt(jsonObj.getString("S5HF"));
			S5MF = Integer.parseInt(jsonObj.getString("S5MF"));
			TS5 = jsonObj.getString("TS5");		
			swS5.setChecked((S5 == 1));
			txtS5.setText(NameS5);
			if(TS5 == "A" )
			{
				txtTS5.setText("Agendado");		
				txtHS5.setText(S5HI + ":" + S5MI + " até " + S5HF + ":" + S5MF);
				
			} else if(TS5 == "M" )
			{
				txtTS5.setText("Manual");					
				txtHS5.setText("");
			}
			else if(TS5 == "P" )
			{
				txtTS5.setText("Pulso");					
				txtHS5.setText(S5P + " segundo(s)");
			}
			
			
			S6 = Integer.parseInt(jsonObj.getString("S6"));
			S6P = Integer.parseInt(jsonObj.getString("S6P"));
			S6HI = Integer.parseInt(jsonObj.getString("S6HI"));
			S6MI = Integer.parseInt(jsonObj.getString("S6MI"));
			S6HF = Integer.parseInt(jsonObj.getString("S6HF"));
			S6MF = Integer.parseInt(jsonObj.getString("S6MF"));
			TS6 = jsonObj.getString("TS6");		
			swS6.setChecked((S6 == 1));
			txtS6.setText(NameS6);
			if(TS6 == "A" )
			{
				txtTS6.setText("Agendado");		
				txtHS6.setText(S6HI + ":" + S6MI + " até " + S6HF + ":" + S6MF);
				
			} else if(TS6 == "M" )
			{
				txtTS6.setText("Manual");					
				txtHS6.setText("");
			}
			else if(TS6 == "P" )
			{
				txtTS6.setText("Pulso");					
				txtHS6.setText(S6P + " segundo(s)");
			}
			
			S7 = Integer.parseInt(jsonObj.getString("S7"));
			S7P = Integer.parseInt(jsonObj.getString("S7P"));
			S7HI = Integer.parseInt(jsonObj.getString("S7HI"));
			S7MI = Integer.parseInt(jsonObj.getString("S7MI"));
			S7HF = Integer.parseInt(jsonObj.getString("S7HF"));
			S7MF = Integer.parseInt(jsonObj.getString("S7MF"));
			TS7 = jsonObj.getString("TS7");		
			swS7.setChecked((S7 == 1));
			txtS7.setText(NameS7);
			if(TS7 == "A" )
			{
				txtTS7.setText("Agendado");		
				txtHS7.setText(S7HI + ":" + S7MI + " até " + S7HF + ":" + S7MF);
				
			} else if(TS7 == "M" )
			{
				txtTS7.setText("Manual");					
				txtHS7.setText("");
			}
			else if(TS7 == "P" )
			{
				txtTS7.setText("Pulso");					
				txtHS7.setText(S7P + " segundo(s)");
			}
			
			
			S8 = Integer.parseInt(jsonObj.getString("S8"));
			S8P = Integer.parseInt(jsonObj.getString("S8P"));
			S8HI = Integer.parseInt(jsonObj.getString("S8HI"));
			S8MI = Integer.parseInt(jsonObj.getString("S8MI"));
			S8HF = Integer.parseInt(jsonObj.getString("S8HF"));
			S8MF = Integer.parseInt(jsonObj.getString("S8MF"));
			TS8 = jsonObj.getString("TS8");		
			swS8.setChecked((S8 == 1));
			txtS8.setText(NameS8);
			if(TS8 == "A" )
			{
				txtTS8.setText("Agendado");		
				txtHS8.setText(S8HI + ":" + S8MI + " até " + S8HF + ":" + S8MF);
				
			} else if(TS8 == "M" )
			{
				txtTS8.setText("Manual");					
				txtHS8.setText("");
			}
			else if(TS8 == "P" )
			{
				txtTS8.setText("Pulso");					
				txtHS8.setText(S8P + " segundo(s)");
			}
			
            SRed = Integer.parseInt(jsonObj.getString("Red"));
            SGreen = Integer.parseInt(jsonObj.getString("Green"));
            SBlue = Integer.parseInt(jsonObj.getString("Blue"));
			
			Data = jsonObj.getString("Day") + "/" + jsonObj.getString("Mounth") + "/" + jsonObj.getString("Year");
			Hora = jsonObj.getString("Hour") + ":" + jsonObj.getString("Minute") + ":" + jsonObj.getString("Second");            
			txtHorario.setText("Horário placa: " + Data + "  " + Hora);
						
			String Temperatura = jsonObj.getString("temperatura");
            String Umidade = jsonObj.getString("umidade");
							
            if (FirstTime) {

                seekBarR.setProgress(SRed / 28);
                seekBarR.refreshDrawableState();

                seekBarG.setProgress(SGreen / 28);
                seekBarG.refreshDrawableState();

                seekBarB.setProgress(SBlue / 28);
                seekBarB.refreshDrawableState();
                FirstTime = false;

                sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
				                
				editor.putString("S1HI", Integer.toString(S1HI));
				editor.putString("S1MI", Integer.toString(S1MI));
				editor.putString("S1HF", Integer.toString(S1HF));
				editor.putString("S1MF", Integer.toString(S1MF));
				editor.putString("TS1", TS1);
				editor.putString("S1P", Integer.toString(S1P));
				
				editor.putString("S2HI", Integer.toString(S2HI));
				editor.putString("S2MI", Integer.toString(S2MI));
				editor.putString("S2HF", Integer.toString(S2HF));
				editor.putString("S2MF", Integer.toString(S2MF));
				editor.putString("TS2", TS2);
				editor.putString("S2P", Integer.toString(S2P));
				
				editor.putString("S3HI", Integer.toString(S3HI));
				editor.putString("S3MI", Integer.toString(S3MI));
				editor.putString("S3HF", Integer.toString(S3HF));
				editor.putString("S3MF", Integer.toString(S3MF));
				editor.putString("TS3", TS3);
				editor.putString("S3P", Integer.toString(S3P));
								
				editor.putString("S4HI", Integer.toString(S4HI));
				editor.putString("S4MI", Integer.toString(S4MI));
				editor.putString("S4HF", Integer.toString(S4HF));
				editor.putString("S4MF", Integer.toString(S4MF));
				editor.putString("TS4", TS4);
				editor.putString("S4P", Integer.toString(S4P));
				
				editor.putString("S5HI", Integer.toString(S5HI));
				editor.putString("S5MI", Integer.toString(S5MI));
				editor.putString("S5HF", Integer.toString(S5HF));
				editor.putString("S5MF", Integer.toString(S5MF));
				editor.putString("TS5", TS5);
				editor.putString("S5P", Integer.toString(S5P));
				editor.putString("S5P", Integer.toString(S5P));

				editor.putString("S6HI", Integer.toString(S6HI));
				editor.putString("S6MI", Integer.toString(S6MI));
				editor.putString("S6HF", Integer.toString(S6HF));
				editor.putString("S6MF", Integer.toString(S6MF));
				editor.putString("TS6", TS6);
				editor.putString("S6P", Integer.toString(S6P));
				
				editor.putString("S7HI", Integer.toString(S7HI));
				editor.putString("S7MI", Integer.toString(S7MI));
				editor.putString("S7HF", Integer.toString(S7HF));
				editor.putString("S7MF", Integer.toString(S7MF));
				editor.putString("TS7", TS7);
				editor.putString("S7P", Integer.toString(S7P));
				
				editor.putString("S8HI", Integer.toString(S8HI));
				editor.putString("S8MI", Integer.toString(S8MI));
				editor.putString("S8HF", Integer.toString(S8HF));
				editor.putString("S8MF", Integer.toString(S8MF));
				editor.putString("TS8", TS8);
				editor.putString("S8P", Integer.toString(S8P));
											
                editor.commit();
            }

        }
        catch (Exception ex) {
           // Toast.makeText(this, "Erro na reconexão de parametros!", Toast.LENGTH_LONG).show();
        }
    }
	
	
	
}
