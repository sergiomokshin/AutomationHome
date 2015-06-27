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

    private int ValueS1;
    private int ValueS2;
    private int ValueS3;
    private int ValueS4;
    private int ValueS5;
    private int ValueS6;
    private int ValueS7;
    private int ValueS8;
    private int ValueSR;
    private int ValueSG;
    private int ValueSB;
    private int ValueSaveAuto;

    private String ValueS1HrI = "0";
    private String ValueS1HrF = "0";
    private String ValueS2HrI = "0";
    private String ValueS2HrF = "0";
    private String ValueS3HrI = "0";
    private String ValueS3HrF = "0";
    private String ValueS4HrI = "0";
    private String ValueS4HrF = "0";
    private String ValueS5HrI = "0";
    private String ValueS5HrF = "0";
    private String ValueS6HrI = "0";
    private String ValueS6HrF = "0";
    private String ValueS7HrI = "0";
    private String ValueS7HrF = "0";
    private String ValueS8HrI = "0";
    private String ValueS8HrF = "0";
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
                startActivity(new Intent(this, DeviceListActivity.class));
                //startActivity(new Intent(this, DeviceListActivity.class),  REQUEST_CONNECT_DEVICE);
                return true;
            case R.id.SetupDevice:
                //startActivity(new Intent(this, SetupDevice.class), REQUEST_SETUP_DEVICE);
                startActivity(new Intent(this, SetupDevice.class));
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
        switch (requestCode) {
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
                //			AtualizaHorariosPlaca();
                AtualizaLabels();
                break;
        }
        AtualizaLabels();
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
            StatusS = (ValueS1 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "2") {
            StatusS = (ValueS2 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "3") {
            StatusS = (ValueS3 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "4") {
            StatusS = (ValueS4 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "5") {
            StatusS = (ValueS5 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "6") {
            StatusS = (ValueS6 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "7") {
            StatusS = (ValueS7 == 1) ? "0" : "1";
            comando += StatusS + "|";
        }
        if (S == "8") {
            StatusS = (ValueS8 == 1) ? "0" : "1";
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

        ValueS1 = Integer.parseInt(DataCommand[1]);
        ValueS2 = Integer.parseInt(DataCommand[2]);
        ValueS3 = Integer.parseInt(DataCommand[3]);
        ValueS4 = Integer.parseInt(DataCommand[4]);
        ValueS5 = Integer.parseInt(DataCommand[5]);
        ValueS6 = Integer.parseInt(DataCommand[6]);
        ValueS7 = Integer.parseInt(DataCommand[7]);
        ValueS8 = Integer.parseInt(DataCommand[8]);
        ValueSR = Integer.parseInt(DataCommand[9]);
        ValueSG = Integer.parseInt(DataCommand[10]);
        ValueSB = Integer.parseInt(DataCommand[11]);
        ValueSaveAuto = Integer.parseInt(DataCommand[11]);

        ValueS1HrI = DataCommand[13];
        ValueS1HrF = DataCommand[14];
        ValueS2HrI = DataCommand[15];
        ValueS2HrF = DataCommand[16];
        ValueS3HrI = DataCommand[17];
        ValueS3HrF = DataCommand[18];
        ValueS4HrI = DataCommand[19];
        ValueS4HrF = DataCommand[20];
        ValueS5HrI = DataCommand[21];
        ValueS5HrF = DataCommand[22];
        ValueS6HrI = DataCommand[23];
        ValueS6HrF = DataCommand[24];
        ValueS7HrI = DataCommand[25];
        ValueS7HrF = DataCommand[26];
        ValueS8HrI = DataCommand[27];
        ValueS8HrF = DataCommand[28];
        ValueRGBHrI = DataCommand[29];
        ValueRGBHrF = DataCommand[30];

        if (ValueS1 == 1) {
            btS1.setText(NameS1 + " - ON");
        } else {
            btS1.setText(NameS1 + " - OFF");
        }

        if (ValueS2 == 1) {
            btS2.setText(NameS2 + " - ON");
        } else {
            btS2.setText(NameS2 + " - OFF");
        }

        if (ValueS3 == 1) {
            btS3.setText(NameS3 + " - ON");
        } else {
            btS3.setText(NameS3 + " - OFF");
        }

        if (ValueS4 == 1) {
            btS4.setText(NameS4 + " - ON");
        } else {
            btS4.setText(NameS4 + " - OFF");
        }

        if (ValueS5 == 1) {
            btS5.setText(NameS5 + " - ON");
        } else {
            btS5.setText(NameS5 + " - OFF");
        }

        if (ValueS6 == 1) {
            btS6.setText(NameS6 + " - ON");
        } else {
            btS6.setText(NameS6 + " - OFF");
        }

        if (ValueS7 == 1) {
            btS7.setText(NameS7 + " - ON");
        } else {
            btS7.setText(NameS7 + " - OFF");
        }

        if (ValueS8 == 1) {
            btS8.setText(NameS8 + " - ON");
        } else {
            btS8.setText(NameS8 + " - OFF");
        }

        if (!FirstTime) {

            seekBarR.setProgress(ValueSR / 28);
            seekBarR.refreshDrawableState();

            seekBarG.setProgress(ValueSG / 28);
            seekBarG.refreshDrawableState();

            seekBarB.setProgress(ValueSB / 28);
            seekBarB.refreshDrawableState();
            FirstTime = true;

            sharedPreferences = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();

            editor.putString("S1HrI", ValueS1HrI);
            editor.putString("S1HrF", ValueS1HrF);
            editor.putString("S2HrI", ValueS2HrI);
            editor.putString("S2HrF", ValueS2HrF);
            editor.putString("S3HrI", ValueS3HrI);
            editor.putString("S3HrF", ValueS3HrF);
            editor.putString("S4HrI", ValueS4HrI);
            editor.putString("S4HrF", ValueS4HrF);
            editor.putString("S5HrI", ValueS5HrI);
            editor.putString("S5HrF", ValueS5HrF);
            editor.putString("S6HrI", ValueS6HrI);
            editor.putString("S6HrF", ValueS6HrF);
            editor.putString("S7HrI", ValueS7HrI);
            editor.putString("S7HrF", ValueS7HrF);
            editor.putString("S8HrI", ValueS8HrI);
            editor.putString("S8HrF", ValueS8HrF);
            editor.putString("SRGBHrI", ValueRGBHrI);
            editor.putString("SRGBHrF", ValueRGBHrF);

            editor.commit();
        }

    }
}