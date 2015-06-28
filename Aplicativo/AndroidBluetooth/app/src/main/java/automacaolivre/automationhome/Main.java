//http://www.webplusandroid.com/creating-listview-with-edittext-and-textwatcher-in-android/

package automacaolivre.automationhome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import android.text.TextUtils;


public class Main extends Activity {

    private String NameS1 = "";
    private String NameS2 = "";
    private String NameS3 = "";
    private String NameS4 = "";
    private String NameS5 = "";
    private String NameS6 = "";
    private String NameS7 = "";
    private String NameS8 = "";
    private String NameSRGB = "";

    private Button btS1;
    private Button btS2;
    private Button btS3;
    private Button btS4;
    private Button btS5;
    private Button btS6;
    private Button btS7;
    private Button btS8;
    private Button btSetupBlueTooth;
    private Button btSetupSs;

    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;

    private int S1;
    private int S2;
    private int S3;
    private int S4;
    private int S5;
    private int S6;
    private int S7;
    private int S8;
    private int SR;
    private int SG;
    private int SB;
    private int SaveAuto;

    private String S1HrI = "0";
    private String S1HrF = "0";
    private String S2HrI = "0";
    private String S2HrF = "0";
    private String S3HrI = "0";
    private String S3HrF = "0";
    private String S4HrI = "0";
    private String S4HrF = "0";
    private String S5HrI = "0";
    private String S5HrF = "0";
    private String S6HrI = "0";
    private String S6HrF = "0";
    private String S7HrI = "0";
    private String S7HrF = "0";
    private String S8HrI = "0";
    private String S8HrF = "0";
    private String ValueRGBHrI = "0";
    private String ValueRGBHrF = "0";


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

        btS1 = (Button) findViewById(R.id.btS1);
        btS2 = (Button) findViewById(R.id.btS2);
        btS3 = (Button) findViewById(R.id.btS3);
        btS4 = (Button) findViewById(R.id.btS4);
        btS5 = (Button) findViewById(R.id.btS5);
        btS6 = (Button) findViewById(R.id.btS6);
        btS7 = (Button) findViewById(R.id.btS7);
        btS8 = (Button) findViewById(R.id.btS8);
        btSetupBlueTooth = (Button) findViewById(R.id.btSetupBluetooth);
        btSetupSs = (Button) findViewById(R.id.btSetupSaidas);

        seekBarR = (SeekBar) findViewById(R.id.seekR);
        seekBarG = (SeekBar) findViewById(R.id.seekG);
        seekBarB = (SeekBar) findViewById(R.id.seekB);

        AtualizaLabels();

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        FirstTime = true;

        btS1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("5");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("6");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("7");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btS8.setOnClickListener(new OnClickListener() {
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

        btSetupSs.setOnClickListener(new OnClickListener() {
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

        if (address == "") {
            Intent setupIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(setupIntent, REQUEST_CONNECT_DEVICE);
        } else {
            Connect();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DeviceListActivity:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClassName("automacaolivre.automationhome", "automacaolivre.automationhome.DeviceListActivity");
                startActivityForResult(intent,90);
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
        NameSRGB = sharedPreferences.getString("RGB", "ILUMINACAO");

        btS1.setText(NameS1);
        btS2.setText(NameS2);
        btS3.setText(NameS3);
        btS4.setText(NameS4);
        btS5.setText(NameS5);
        btS6.setText(NameS6);
        btS7.setText(NameS7);
        btS8.setText(NameS8);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();

                    editor.putString("ADDRESS", address);
                    editor.commit();

                    Connect();
                }
                break;
            case REQUEST_SETUP_DEVICE:
                AtualizaHorariosPlaca();
                AtualizaLabels();
                break;
        }
    }
    private void AtualizaHorariosPlaca() {

        try {

            sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);

            S1HrI = sharedPreferences.getString("S1HrI", "");
            S1HrF = sharedPreferences.getString("S1HrF", "");
            writeData("|H1I" + String.format("%02d", Integer.parseInt(S1HrI)) + "|");
            writeData("|H1F" + String.format("%02d", Integer.parseInt(S1HrF)) + "|");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void Connect() {
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

        } catch (IOException e) {
            Toast.makeText(this, "Ocorreu um erro durante a conexão com o Bluetooth!", Toast.LENGTH_LONG).show();
        }
    }

    private void EnviarRGB(String Porta, int Progress) throws IOException {

        String comando = "|A" + Porta + String.valueOf(Progress) + "|";
        writeData(comando);

    }

    private void EnviarComandoDigital(String S) throws IOException {

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

    private void writeData(String data) throws IOException {

        try {
            write(data);
        } catch (IOException ex) {
            write(data);
        }
    }

    private void write(String data) throws IOException {

        byte[] msgBuffer = data.getBytes();
        mmOutStream.write(msgBuffer, 0, msgBuffer.length);

    }

    public void beginListenForCommands() {

        try {
            mmInStream = mmSocket.getInputStream();
        } catch (IOException e) {

            Toast.makeText(this, "Dispositivo nao conectado, reconecte!", Toast.LENGTH_LONG).show();
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
        SaveAuto = Integer.parseInt(DataCommand[11]);

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
        ValueRGBHrI = DataCommand[29];
        ValueRGBHrF = DataCommand[30];

        if (S1 == 1) {
            btS1.setText(NameS1 + " - ON");
        } else {
            btS1.setText(NameS1 + " - OFF");
        }

        if (S2 == 1) {
            btS2.setText(NameS2 + " - ON");
        } else {
            btS2.setText(NameS2 + " - OFF");
        }

        if (S3 == 1) {
            btS3.setText(NameS3 + " - ON");
        } else {
            btS3.setText(NameS3 + " - OFF");
        }

        if (S4 == 1) {
            btS4.setText(NameS4 + " - ON");
        } else {
            btS4.setText(NameS4 + " - OFF");
        }

        if (S5 == 1) {
            btS5.setText(NameS5 + " - ON");
        } else {
            btS5.setText(NameS5 + " - OFF");
        }

        if (S6 == 1) {
            btS6.setText(NameS6 + " - ON");
        } else {
            btS6.setText(NameS6 + " - OFF");
        }

        if (S7 == 1) {
            btS7.setText(NameS7 + " - ON");
        } else {
            btS7.setText(NameS7 + " - OFF");
        }

        if (S8 == 1) {
            btS8.setText(NameS8 + " - ON");
        } else {
            btS8.setText(NameS8 + " - OFF");
        }

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
            editor.putString("SRGBHrI", ValueRGBHrI);
            editor.putString("SRGBHrF", ValueRGBHrF);

            editor.commit();
        }

    }
}