//Android 4.0

package automacaolivre.automationhome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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


public class Main extends Activity {

    private String ModoAgendado = "";
	private String Data = "";
	private String Hora = "";
    private Switch swModoAgendado;
	
	private TextView txtHorario;
	
	private int S1;
	private String S1HrI = "0";
    private String S1HrF = "0";
    private String NameS1 = "";	
    private ImageButton imgS1;
    private TextView txtS1;
    private TextView txtS1H;

	private int S2;
	private String S2HrI = "0";
    private String S2HrF = "0";
    private String NameS2 = "";	
    private ImageButton imgS2;
    private TextView txtS2;
    private TextView txtS2H;
	
	private int S3;
	private String S3HrI = "0";
    private String S3HrF = "0";
    private String NameS3 = "";	
    private ImageButton imgS3;
    private TextView txtS3;
    private TextView txtS3H;
	
	private int S4;
	private String S4HrI = "0";
    private String S4HrF = "0";
    private String NameS4 = "";	
    private ImageButton imgS4;
    private TextView txtS4;
    private TextView txtS4H;
	
	private int S5;
	private String S5HrI = "0";
    private String S5HrF = "0";
    private String NameS5 = "";	
    private ImageButton imgS5;
    private TextView txtS5;
    private TextView txtS5H;
	
	private int S6;
	private String S6HrI = "0";
    private String S6HrF = "0";
    private String NameS6 = "";	
    private ImageButton imgS6;
    private TextView txtS6;
    private TextView txtS6H;
	
	private int S7;
	private String S7HrI = "0";
    private String S7HrF = "0";
    private String NameS7 = "";	
    private ImageButton imgS7;
    private TextView txtS7;
    private TextView txtS7H;
	
	private int S8;
	private String S8HrI = "0";
    private String S8HrF = "0";
    private String NameS8 = "";	
    private ImageButton imgS8;
    private TextView txtS8;
    private TextView txtS8H;
	
	private int SRGB;
	private String SRGBHrI = "0";
    private String SRGBHrF = "0";
    private String NameSRGB = "";	
    private TextView txtSRGB;
   // private TextView txtSRGBH;
		
    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;
    private int SR;
    private int SG;
    private int SB;

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
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Conectando com o dispositivo.", Toast.LENGTH_LONG).show();

        swModoAgendado = (Switch)findViewById(R.id.swModoAgendado);
		txtHorario = (TextView)findViewById(R.id.txtHorario);
		
        imgS1 = (ImageButton) findViewById(R.id.imgS1);
        txtS1 = (TextView) findViewById(R.id.txtS1);
        txtS1H = (TextView) findViewById(R.id.txtS1H);
		
		imgS2 = (ImageButton) findViewById(R.id.imgS2);
        txtS2 = (TextView) findViewById(R.id.txtS2);
        txtS2H = (TextView) findViewById(R.id.txtS2H);
		
		imgS3 = (ImageButton) findViewById(R.id.imgS3);
        txtS3 = (TextView) findViewById(R.id.txtS3);
        txtS3H = (TextView) findViewById(R.id.txtS3H);
		
		imgS4 = (ImageButton) findViewById(R.id.imgS4);
        txtS4 = (TextView) findViewById(R.id.txtS4);
        txtS4H = (TextView) findViewById(R.id.txtS4H);
		
		imgS5 = (ImageButton) findViewById(R.id.imgS5);
        txtS5 = (TextView) findViewById(R.id.txtS5);
        txtS5H = (TextView) findViewById(R.id.txtS5H);
		
		imgS6 = (ImageButton) findViewById(R.id.imgS6);
        txtS6 = (TextView) findViewById(R.id.txtS6);
        txtS6H = (TextView) findViewById(R.id.txtS6H);
		
		imgS7 = (ImageButton) findViewById(R.id.imgS7);
        txtS7 = (TextView) findViewById(R.id.txtS7);
        txtS7H = (TextView) findViewById(R.id.txtS7H);
		
		imgS8 = (ImageButton) findViewById(R.id.imgS8);
        txtS8 = (TextView) findViewById(R.id.txtS8);
        txtS8H = (TextView) findViewById(R.id.txtS8H);
		
        txtSRGB = (TextView) findViewById(R.id.txtSRGB);
        //txtSRGBH = (TextView) findViewById(R.id.txtSRGBH);

        seekBarR = (SeekBar) findViewById(R.id.seekR);
        seekBarG = (SeekBar) findViewById(R.id.seekG);
        seekBarB = (SeekBar) findViewById(R.id.seekB);

        AtualizaLabels();

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        FirstTime = true;


        swModoAgendado.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {

                    if (swModoAgendado.isChecked()) {
                        writeData("|M1|");

                    } else {
                        writeData("|M0|");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        imgS1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("5");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("6");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("7");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgS8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        
        if (address == "") {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("automacaolivre.automationhome", "automacaolivre.automationhome.DeviceListActivity");
            startActivityForResult(intent,90);

        } else {
            Connect(false);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DefineHoraData:
			
				sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putString("Data", Data);
				editor.putString("Hora", Hora);
				editor.commit();
							
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClassName("automacaolivre.automationhome", "automacaolivre.automationhome.DefineHoraData");
                startActivityForResult(intent,90);
                return true;
            case R.id.DeviceListActivity:
                Intent intentD = new Intent(Intent.ACTION_VIEW);
                intentD.setClassName("automacaolivre.automationhome", "automacaolivre.automationhome.DeviceListActivity");
                startActivityForResult(intentD,90);
                return true;
            case R.id.SetupDevice:
                Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhome", "automacaolivre.automationhome.SetupDevice");
                startActivityForResult(intentS,90);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void AtualizaLabels() {

        //final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
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
        NameSRGB = sharedPreferences.getString("SRGB", "ILUMINACAO");

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
                    AtualizaAgendamentosPlaca();
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

    private void AtualizaAgendamentosPlaca() throws InterruptedException {

        try {

            sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);

            S1HrI = sharedPreferences.getString("S1HrI", "");
            S1HrF = sharedPreferences.getString("S1HrF", "");

            if(writeData("|H1I" + String.format("%02d", Integer.parseInt(S1HrI)) + "|")) {
                Thread.sleep(100);
                writeData("|H1F" + String.format("%02d", Integer.parseInt(S1HrF)) + "|");
                Thread.sleep(100);

                S2HrI = sharedPreferences.getString("S2HrI", "");
                S2HrF = sharedPreferences.getString("S2HrF", "");
                writeData("|H2I" + String.format("%02d", Integer.parseInt(S2HrI)) + "|");
                Thread.sleep(100);
                writeData("|H2F" + String.format("%02d", Integer.parseInt(S2HrF)) + "|");
                Thread.sleep(100);

                S3HrI = sharedPreferences.getString("S3HrI", "");
                S3HrF = sharedPreferences.getString("S3HrF", "");
                writeData("|H3I" + String.format("%02d", Integer.parseInt(S3HrI)) + "|");
                Thread.sleep(100);
                writeData("|H3F" + String.format("%02d", Integer.parseInt(S3HrF)) + "|");
                Thread.sleep(100);

                S4HrI = sharedPreferences.getString("S4HrI", "");
                S4HrF = sharedPreferences.getString("S4HrF", "");
                writeData("|H4I" + String.format("%02d", Integer.parseInt(S4HrI)) + "|");
                Thread.sleep(100);
                writeData("|H4F" + String.format("%02d", Integer.parseInt(S4HrF)) + "|");
                Thread.sleep(100);

                S5HrI = sharedPreferences.getString("S5HrI", "");
                S5HrF = sharedPreferences.getString("S5HrF", "");
                writeData("|H5I" + String.format("%02d", Integer.parseInt(S5HrI)) + "|");
                Thread.sleep(100);
                writeData("|H5F" + String.format("%02d", Integer.parseInt(S5HrF)) + "|");
                Thread.sleep(100);

                S6HrI = sharedPreferences.getString("S6HrI", "");
                S6HrF = sharedPreferences.getString("S6HrF", "");
                writeData("|H6I" + String.format("%02d", Integer.parseInt(S6HrI)) + "|");
                Thread.sleep(100);
                writeData("|H6F" + String.format("%02d", Integer.parseInt(S6HrF)) + "|");
                Thread.sleep(100);

                S7HrI = sharedPreferences.getString("S7HrI", "");
                S7HrF = sharedPreferences.getString("S7HrF", "");
                writeData("|H7I" + String.format("%02d", Integer.parseInt(S7HrI)) + "|");
                Thread.sleep(100);
                writeData("|H7F" + String.format("%02d", Integer.parseInt(S7HrF)) + "|");
                Thread.sleep(100);

                S8HrI = sharedPreferences.getString("S8HrI", "");
                S8HrF = sharedPreferences.getString("S8HrF", "");
                writeData("|H8I" + String.format("%02d", Integer.parseInt(S8HrI)) + "|");
                Thread.sleep(100);
                if(writeData("|H8F" + String.format("%02d", Integer.parseInt(S8HrF)) + "|")) {
                    Toast.makeText(getApplicationContext(), "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Erro durante a atualização dos parametros na placa, tente novamente!", Toast.LENGTH_SHORT).show();
                }

                //SRGBHrI = sharedPreferences.getString("SRGBHrI", "");
                //SRGBHrF = sharedPreferences.getString("SRGBHrF", "");
               // writeData("|H9I" + String.format("%02d", Integer.parseInt(SRGBHrI)) + "|");
                //Thread.sleep(100);

                //if(writeData("|H9F" + String.format("%02d", Integer.parseInt(SRGBHrF)) + "|")) {
                //    Toast.makeText(getApplicationContext(), "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();
               // }
               // else {
                //    Toast.makeText(getApplicationContext(), "Erro durante a atualização dos parametros na placa, tente novamente!", Toast.LENGTH_SHORT).show();
               // }
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


        if(ModoAgendado.contains("1")) {
            Toast.makeText(getApplicationContext(), "Modo agendado não permite acionamento de comandos, altere para o modo Manual!", Toast.LENGTH_SHORT).show();
            return;
        }
        //|D21|
        String comando = "|D" + S;

        String StatusS = "";

        if (S == "1") {
            StatusS = (S1 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "2") {
            StatusS = (S2 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "3") {
            StatusS = (S3 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "4") {
            StatusS = (S4 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "5") {
            StatusS = (S5 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "6") {
            StatusS = (S6 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "7") {
            StatusS = (S7 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "8") {
            StatusS = (S8 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }

        //int duracao = Toast.LENGTH_SHORT;
        //Toast toast = Toast.makeText(getApplicationContext(), comando, duracao);
        //toast.show();

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

                                            if (data.contains("COMANDOS")) {
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
            String[] DataCommand = data.split("#");

            S1 = Integer.parseInt(DataCommand[1]);
            S2 = Integer.parseInt(DataCommand[2]);
            S3 = Integer.parseInt(DataCommand[3]);
            S4 = Integer.parseInt(DataCommand[4]);
            S5 = Integer.parseInt(DataCommand[5]);
            S6 = Integer.parseInt(DataCommand[6]);
            S7 = Integer.parseInt(DataCommand[7]);
            S8 = Integer.parseInt(DataCommand[8]);
            SR = Integer.parseInt(DataCommand[9]);
            SG = Integer.parseInt(DataCommand[10]);
            SB = Integer.parseInt(DataCommand[11]);
            ModoAgendado = DataCommand[12].toString();

            S1HrI = DataCommand[13];
            S1HrF = DataCommand[14];
            S2HrI = DataCommand[15];
            S2HrF = DataCommand[16];
            S3HrI = DataCommand[17];
            S3HrF = DataCommand[18];
            S4HrI = DataCommand[19];
            S4HrF = DataCommand[20];
            S5HrI = DataCommand[21];
            S5HrF = DataCommand[22];
            S6HrI = DataCommand[23];
            S6HrF = DataCommand[24];
            S7HrI = DataCommand[25];
            S7HrF = DataCommand[26];
            S8HrI = DataCommand[27];
            S8HrF = DataCommand[28];
           // SRGBHrI = DataCommand[29];
           // SRGBHrF = DataCommand[30];

            Data = DataCommand[31];
            Hora = DataCommand[32];

            txtHorario.setText("Horário placa: " + Data + "  " + Hora);

            if (ModoAgendado.contains("1"))
                swModoAgendado.setChecked(true);
            else
                swModoAgendado.setChecked(false);

            if (S1 == 1) {
                imgS1.setImageResource(R.drawable.on);
            } else {
                imgS1.setImageResource(R.drawable.off);
            }
            txtS1.setText(NameS1);
            txtS1H.setText(S1HrI + ":59 até " + S1HrF + ":59");

            if (S2 == 1) {
                imgS2.setImageResource(R.drawable.on);
            } else {
                imgS2.setImageResource(R.drawable.off);
            }
            txtS2.setText(NameS2);
            txtS2H.setText(S2HrI + ":59 até " + S2HrF + ":59");

            if (S3 == 1) {
                imgS3.setImageResource(R.drawable.on);
            } else {
                imgS3.setImageResource(R.drawable.off);
            }
            txtS3.setText(NameS3);
            txtS3H.setText(S3HrI + ":59 até " + S3HrF + ":59");

            if (S4 == 1) {
                imgS4.setImageResource(R.drawable.on);
            } else {
                imgS4.setImageResource(R.drawable.off);
            }
            txtS4.setText(NameS4);
            txtS4H.setText(S4HrI + ":59 até " + S4HrF + ":59");

            if (S5 == 1) {
                imgS5.setImageResource(R.drawable.on);
            } else {
                imgS5.setImageResource(R.drawable.off);
            }
            txtS5.setText(NameS5);
            txtS5H.setText(S5HrI + ":59 até " + S5HrF + ":59");

            if (S6 == 1) {
                imgS6.setImageResource(R.drawable.on);
            } else {
                imgS6.setImageResource(R.drawable.off);
            }
            txtS6.setText(NameS6);
            txtS6H.setText(S6HrI + ":59 até " + S6HrF + ":59");

            if (S7 == 1) {
                imgS7.setImageResource(R.drawable.on);
            } else {
                imgS7.setImageResource(R.drawable.off);
            }
            txtS7.setText(NameS7);
            txtS7H.setText(S7HrI + ":59 até " + S7HrF + ":59");

            if (S8 == 1) {
                imgS8.setImageResource(R.drawable.on);
            } else {
                imgS8.setImageResource(R.drawable.off);
            }
            txtS8.setText(NameS8);
            txtS8H.setText(S8HrI + ":59 até " + S8HrF + ":59");

            txtSRGB.setText(NameSRGB);
           // txtSRGBH.setText(SRGBHrI + ":59 até " + SRGBHrF + ":59");


            if (FirstTime) {

                seekBarR.setProgress(SR / 28);
                seekBarR.refreshDrawableState();

                seekBarG.setProgress(SG / 28);
                seekBarG.refreshDrawableState();

                seekBarB.setProgress(SB / 28);
                seekBarB.refreshDrawableState();
                FirstTime = false;

                sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor = sharedPreferences.edit();

                editor.putString("S1HrI", S1HrI);
                editor.putString("S1HrF", S1HrF);
                editor.putString("S2HrI", S2HrI);
                editor.putString("S2HrF", S2HrF);
                editor.putString("S3HrI", S3HrI);
                editor.putString("S3HrF", S3HrF);
                editor.putString("S4HrI", S4HrI);
                editor.putString("S4HrF", S4HrF);
                editor.putString("S5HrI", S5HrI);
                editor.putString("S5HrF", S5HrF);
                editor.putString("S6HrI", S6HrI);
                editor.putString("S6HrF", S6HrF);
                editor.putString("S7HrI", S7HrI);
                editor.putString("S7HrF", S7HrF);
                editor.putString("S8HrI", S8HrI);
                editor.putString("S8HrF", S8HrF);
                editor.putString("SRGBHrI", SRGBHrI);
                editor.putString("SRGBHrF", SRGBHrF);

                editor.commit();
            }

        }
        catch (Exception ex) {
           // Toast.makeText(this, "Erro na reconexão de parametros!", Toast.LENGTH_LONG).show();
        }
    }
}