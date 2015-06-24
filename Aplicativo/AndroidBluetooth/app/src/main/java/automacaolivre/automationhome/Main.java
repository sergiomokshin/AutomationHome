package automacaolivre.automationhome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.os.Bundle;
import android.app.Activity;
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

public class Main extends Activity {

    private String StatusSaida1 = "0";
    private String StatusSaida2 = "0";
    private String StatusSaida3 = "0";
    private String StatusSaida4 = "0";
	private String StatusSaida5 = "0";
	private String StatusSaida6 = "0";
	private String StatusSaida7 = "0";
	private String StatusSaida8 = "0";
	
	private String NameSaida1 = "Saída 1";
    private String NameSaida2 = "Saída 2";
    private String NameSaida3 = "Saída 3";
    private String NameSaida4 = "Saída 4";
	private String NameSaida5 = "Saída 5";
	private String NameSaida6 = "Saída 6";
	private String NameSaida7 = "Saída 7";
	private String NameSaida8 = "Saída 8";
	private String NameSaidaRGB = "Iluminação";
		
	private Button btSaida1;
    private Button btSaida2;
    private Button btSaida3;
    private Button btSaida4;
	private Button btSaida5;
	private Button btSaida6;
	private Button btSaida7;
	private Button btSaida8;
	private Button btSetupBlueTooth;
	private Button btSetupSaidas;

    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;

    private int ValueSaida1;
    private int ValueSaida2;
    private int ValueSaida3;
    private int ValueSaida4;
	private int ValueSaida5;
	private int ValueSaida6;
	private int ValueSaida7;
	private int ValueSaida8;
    private int ValueSaidaR;
    private int ValueSaidaG;
    private int ValueSaidaB;
	
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
	private BluetoothAdapter meuAdaptadorBluetooth = null;

    private BluetoothSocket mmSocket = null;
    private BluetoothDevice mmDevice = null;

    private InputStream mmInStream = null;
    private OutputStream mmOutStream = null;
	
	SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;


    private UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
								
        btSaida1 = (Button) findViewById(R.id.btSaida1);
        btSaida2 = (Button) findViewById(R.id.btSaida2);
        btSaida3 = (Button) findViewById(R.id.btSaida3);
        btSaida4 = (Button) findViewById(R.id.btSaida4);
		btSaida5 = (Button) findViewById(R.id.btSaida5);
		btSaida6 = (Button) findViewById(R.id.btSaida6);
		btSaida7 = (Button) findViewById(R.id.btSaida7);
		btSaida8 = (Button) findViewById(R.id.btSaida8);
        btSetupBlueTooth = (Button) findViewById(R.id.btSetupBluetooth);
		btSetupSaidas = (Button) findViewById(R.id.btSetupSaidas);
					
        seekBarR = (SeekBar) findViewById(R.id.seekR);
        seekBarG = (SeekBar) findViewById(R.id.seekG);
        seekBarB = (SeekBar) findViewById(R.id.seekB);

		//sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS",Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();
		
		//NameSaida1 = sharedPreferences.getString(PREF_SAIDA1,"Sa�da 1");
		//NameSaida2 = sharedPreferences.getString(PREF_SAIDA2,"Sa�da 2");
		//NameSaida3 = sharedPreferences.getString(PREF_SAIDA3,"Sa�da 3");
		//NameSaida4 = sharedPreferences.getString(PREF_SAIDA4,"Sa�da 4");
		//NameSaida5 = sharedPreferences.getString(PREF_SAIDA5,"Sa�da 5");
		//NameSaida6 = sharedPreferences.getString(PREF_SAIDA6,"Sa�da 6");
		//NameSaida7 = sharedPreferences.getString(PREF_SAIDA7,"Sa�da 7");
		//NameSaida8 = sharedPreferences.getString(PREF_SAIDA8,"Sa�da 8");
				
		//editor.putString(PREF_IP,ipAddress); // set the ip address value to save
        //editor.putString(PREF_PORT,portNumber); // set the port number to save
        //editor.commit(); //

		        			
		AtualizaLabels();
				
        txtMsg = (TextView) findViewById(R.id.txtMsg);		
		FirstTime = true;
		
		 btSaida1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btSaida2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btSaida3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btSaida4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });			
		
		btSaida5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("5");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		btSaida6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("6");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		btSaida7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("7");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		btSaida8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		btSetupBlueTooth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                
				meuAdaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();						
			//	Intent setupIntent = new Intent(this, DeviceListActivity.class);
			//	startActivityForResult(setupIntent, REQUEST_CONNECT_DEVICE);
            }
        });
		
		btSetupSaidas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
			//	Intent serverIntent = new Intent(this, SetupDevice.class);
			//	startActivityForResult(serverIntent, REQUEST_SETUP_DEVICE);
            }
        });

        seekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

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
        });

        seekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

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
        });

        seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

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
        });
		
		meuAdaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();
		address = sharedPreferences.getString("ADDRESS", "");

		if(address == "")
		{				
			Intent setupIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(setupIntent, REQUEST_CONNECT_DEVICE);
		}
		else
		{		
			Connect();
		}
    }
	
	private void AtualizaLabels(){


        sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        NameSaida1 = sharedPreferences.getString("NAME_SAIDA1","Saída 1");
        NameSaida2 = sharedPreferences.getString("NAME_SAIDA2","Saída 2");
        NameSaida3 = sharedPreferences.getString("NAME_SAIDA3","Saída 3");
        NameSaida4 = sharedPreferences.getString("NAME_SAIDA4","Saída 4");
        NameSaida5 = sharedPreferences.getString("NAME_SAIDA5","Saída 5");
        NameSaida6 = sharedPreferences.getString("NAME_SAIDA6","Saída 6");
        NameSaida7 = sharedPreferences.getString("NAME_SAIDA7","Saída 7");
        NameSaida8 = sharedPreferences.getString("NAME_SAIDA8","Saída 8");
        NameSaidaRGB = sharedPreferences.getString("NAME_SAIDARGB","ILUMINACAO");
/*
		NameSaida1 = ApplicationPreferences.getInstance().getNameSaida1();
		NameSaida2 = ApplicationPreferences.getInstance().getNameSaida2();
		NameSaida3 = ApplicationPreferences.getInstance().getNameSaida3();
		NameSaida4 = ApplicationPreferences.getInstance().getNameSaida4();
		NameSaida5 = ApplicationPreferences.getInstance().getNameSaida5();
		NameSaida6 = ApplicationPreferences.getInstance().getNameSaida6();
		NameSaida7 = ApplicationPreferences.getInstance().getNameSaida7();
		NameSaida8 = ApplicationPreferences.getInstance().getNameSaida8();
		NameSaidaRGB = ApplicationPreferences.getInstance().getNameSaidaRGB();
*/
		btSaida1.setText(NameSaida1);
		btSaida2.setText(NameSaida2);
		btSaida3.setText(NameSaida3);
		btSaida4.setText(NameSaida4);
		btSaida5.setText(NameSaida5);
		btSaida6.setText(NameSaida6);
		btSaida7.setText(NameSaida7);
		btSaida8.setText(NameSaida8);
	}
	

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	
        switch (requestCode) {
			case REQUEST_CONNECT_DEVICE:
				if (resultCode == Activity.RESULT_OK) {						
					address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);	
					//ApplicationPreferences.getInstance().setDeviceAddress(address);
					Connect();																					
				}
				break;
			case REQUEST_SETUP_DEVICE:
					AtualizaLabels();
				break;
        }
    }
	
	private void Connect()
	{

		meuAdaptadorBluetooth.cancelDiscovery();		
		mmDevice = meuAdaptadorBluetooth.getRemoteDevice(address);
		
		try {
			mmSocket = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
			mmSocket.connect();
			mmInStream = mmSocket.getInputStream();
			mmOutStream = mmSocket.getOutputStream();
			
			String enviada = "";
			byte[] send = enviada.getBytes();
			mmOutStream.write(send);

			beginListenForCommands();

		}
		catch(IOException e){
			Toast.makeText(this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
		}
	}
	
	private void EnviarRGB(String Porta, int Progress) throws IOException {
        String comando = "|A" + Porta + String.valueOf(Progress) + "|";
        writeData(comando);
    }

    private void EnviarComandoDigital(String Saida) throws IOException {
        //|D21|
        String comando = "|D" + Saida;

        if (Saida == "1") {
            StatusSaida1 = (ValueSaida1 == 1) ? "0" : "1";
            comando += StatusSaida1 + "|";
        }
        if (Saida == "2") {
            StatusSaida2 = (ValueSaida2 == 1) ? "0" : "1";
            comando += StatusSaida2 + "|";
        }
        if (Saida == "3") {
            StatusSaida3 = (ValueSaida3 == 1) ? "0" : "1";
            comando += StatusSaida3 + "|";
        }
        if (Saida == "4") {
            StatusSaida4 = (ValueSaida4 == 1) ? "0" : "1";
            comando += StatusSaida4 + "|";
        }
		if (Saida == "5") {
            StatusSaida5 = (ValueSaida5 == 1) ? "0" : "1";
            comando += StatusSaida5 + "|";
        }
		if (Saida == "6") {
            StatusSaida6 = (ValueSaida6 == 1) ? "0" : "1";
            comando += StatusSaida6 + "|";
        }
		if (Saida == "7") {
            StatusSaida7 = (ValueSaida7 == 1) ? "0" : "1";
            comando += StatusSaida7 + "|";
        }
		if (Saida == "8") {
            StatusSaida8 = (ValueSaida8 == 1) ? "0" : "1";
            comando += StatusSaida8 + "|";
        }

        //int duracao = Toast.LENGTH_SHORT;
        //Toast toast = Toast.makeText(getApplicationContext(), comando, duracao);
        //toast.show();

        writeData(comando);
    }
	
	private void writeData(String data) throws IOException {

        try {
            write(data);
        } catch (IOException ex) {
            write(data);
        }
    }

    private void write(String data) throws IOException {
        //OutputStream outStream = btSocket.getOutputStream();
        byte[] msgBuffer = data.getBytes();
        mmOutStream.write(msgBuffer, 0, msgBuffer.length);

    }
	
	public void beginListenForCommands()   {
		
        try {
            mmInStream = mmSocket.getInputStream();			
        } catch (IOException e) {
					
			Toast.makeText(this, "Dispositivo n�o conectado, reconecte!", Toast.LENGTH_LONG).show();
        }

        Thread workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {					
                        int bytesAvailable = mmInStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;
                                    handler.post(new Runnable()
                                    {
                                        public void run() {

                                            if (data.contains("COMANDOS")) {


                                                    String[] DataCommand = data.split("#");

                                                    ValueSaida1 = Integer.parseInt(DataCommand[1]);
                                                    ValueSaida2 = Integer.parseInt(DataCommand[2]);
                                                    ValueSaida3 = Integer.parseInt(DataCommand[3]);
                                                    ValueSaida4 = Integer.parseInt(DataCommand[4]);
                                                    ValueSaida5 = Integer.parseInt(DataCommand[5]);
                                                    ValueSaida6 = Integer.parseInt(DataCommand[6]);
                                                    ValueSaida7 = Integer.parseInt(DataCommand[7]);
                                                    ValueSaida8 = Integer.parseInt(DataCommand[8]);
                                                    ValueSaidaR = Integer.parseInt(DataCommand[9]);
                                                    ValueSaidaG = Integer.parseInt(DataCommand[10]);
                                                    ValueSaidaB = Integer.parseInt(DataCommand[11]);

                                                    if (ValueSaida1 == 1) {
                                                        btSaida1.setText(NameSaida1 + " - ON");
                                                    } else {
                                                        btSaida1.setText(NameSaida1 + " - OFF");
                                                    }

                                                    if (ValueSaida2 == 1) {
                                                        btSaida2.setText(NameSaida2 + " - ON");
                                                    } else {
                                                        btSaida2.setText(NameSaida2 + " - OFF");
                                                    }

                                                    if (ValueSaida3 == 1) {
                                                        btSaida3.setText(NameSaida3 + " - ON");
                                                    } else {
                                                        btSaida3.setText(NameSaida3 + " - OFF");
                                                    }

                                                    if (ValueSaida4 == 1) {
                                                        btSaida4.setText(NameSaida4 + " - ON");
                                                    } else {
                                                        btSaida4.setText(NameSaida4 + " - OFF");
                                                    }

                                                    if (ValueSaida5 == 1) {
                                                        btSaida5.setText(NameSaida5 + " - ON");
                                                    } else {
                                                        btSaida5.setText(NameSaida5 + " - OFF");
                                                    }

                                                    if (ValueSaida6 == 1) {
                                                        btSaida6.setText(NameSaida6 + " - ON");
                                                    } else {
                                                        btSaida6.setText(NameSaida6 + " - OFF");
                                                    }

                                                    if (ValueSaida7 == 1) {
                                                        btSaida7.setText(NameSaida7 + " - ON");
                                                    } else {
                                                        btSaida7.setText(NameSaida7 + " - OFF");
                                                    }

                                                    if (ValueSaida8 == 1) {
                                                        btSaida8.setText(NameSaida8 + " - ON");
                                                    } else {
                                                        btSaida8.setText(NameSaida8 + " - OFF");
                                                    }

                                                    if (FirstTime == false) {

                                                        seekBarR.setProgress(ValueSaidaR / 28);
                                                        seekBarR.refreshDrawableState();

                                                        seekBarG.setProgress(ValueSaidaG / 28);
                                                        seekBarG.refreshDrawableState();

                                                        seekBarB.setProgress(ValueSaidaB / 28);
                                                        seekBarB.refreshDrawableState();
                                                        FirstTime = true;
                                                    }

                                            }

                                           //txtMsg.setText(data);
                                             txtMsg.setText("");
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
						//Toast.makeText(this, "Ocorreu um erro durante o recebimendo dos dados do dispositivo!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        workerThread.start();
    }
	
}