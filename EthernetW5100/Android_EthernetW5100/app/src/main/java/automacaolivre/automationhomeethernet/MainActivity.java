package automacaolivre.automationhomeethernet;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {


	private Timer timer  = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();
	
    private String ModoAgendado = "";
    private String Data = "";
    private String Hora = "";
    private Switch swModoAgendado;
	private String Temperatura = "";
	private String Umidade = "";
	
    private TextView txtHorario;
	private TextView txtTemperatura;

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

	
	private ImageButton imgWhite;
	private ImageButton imgBlue;
	private ImageButton imgRed;
	private ImageButton imgGreen;
	private ImageButton imgOff;
	
    private int SR;
    private int SG;
    private int SB;

    String ip;
    private Boolean FirstTime;

    private TextView txtMsg;

    private final int REQUEST_SETUP_IP = 1;
    private final int REQUEST_SETUP_DEVICE = 2;
    private final int REQUEST_SETUP_DATETIME = 3;
	
	private Boolean EmExecucao;


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Conectando com o dispositivo.", Toast.LENGTH_LONG).show();

        swModoAgendado = (Switch) findViewById(R.id.swModoAgendado);
        txtHorario = (TextView) findViewById(R.id.txtHorario);
		txtTemperatura = (TextView) findViewById(R.id.txtTemperatura);

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

        imgWhite = (ImageButton) findViewById(R.id.imgWhite);
		imgBlue = (ImageButton) findViewById(R.id.imgBlue);
		imgRed = (ImageButton) findViewById(R.id.imgRed);
		imgGreen = (ImageButton) findViewById(R.id.imgGreen);
		imgOff = (ImageButton) findViewById(R.id.imgOff);
        
        AtualizaLabels();
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        FirstTime = true;

        swModoAgendado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String ipAtual = sharedPreferences.getString("IP", "");
                if (swModoAgendado.isChecked()) {

                    ConectarPlaca("http://" + ip + "/?AUTOL", true);

                } else {
                    ConectarPlaca("http://" + ip + "/?AUTOD", true);
                }
            }
        });


        imgS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("5");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("6");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("7");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imgS8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


       imgWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("WHI");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("BLU");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("GREE");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		 imgRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("RED");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
		
		imgOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    EnviarComandoDigital("RGBOFF");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });	  
		
		ip = sharedPreferences.getString("IP", "");
		EmExecucao = false;
		if(!isConnected())
		{
			Toast.makeText(this, "Dispositivo Android não conectado! Conecte com a rede antes de abrir o aplicativo de automação!", Toast.LENGTH_LONG).show();		
			return;
		}

        ip = "192.168.0.202";
		        
        if (ip.equals("")) {
			ip = "192.168.0.202";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("automacaolivre.automationhomeethernet", "automacaolivre.automationhomeethernet.IPActivity");
            startActivityForResult(intent, 90);
        } else {

			if(ConectarPlaca("http://" + ip, true))
			{
				IniciaTimer();		
			}
        }				
    }

	 private void IniciaTimer(){
        task = new TimerTask() {
            public void run() {
                    handler.post(new Runnable() {
                            public void run() {
								if(!ip.equals("")){
									if(!EmExecucao)
									{
										ConectarPlaca("http://" + ip, false);									
									}								
								}												                                													
                            }
                   });
            }};           
			
            timer.schedule(task, 60000, 60000);
    }

    public Boolean ConectarPlaca(String url, Boolean ExibirMensagem){
        InputStream inputStream = null;
        String result = "";
		
		EmExecucao = true;
        try {

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
                AtualizaDadosPlaca(result);
            }
            else {
				if(ExibirMensagem){
					Toast.makeText(this, "Dados truncados, verifique se a placa está conectada corretamente e se a rede não apresenta problemas!", Toast.LENGTH_LONG).show();		
				}
				EmExecucao = false;
				return false;
            }
        } catch (Exception e) {
			EmExecucao = false;
			if(ExibirMensagem){
				Toast.makeText(this, "Erro durante a conexão com a placa, verifique se o IP está correto e se dispositivo Android tem acesso a mesma rede da placa.!", Toast.LENGTH_LONG).show();
			}
			return false;
        }
		EmExecucao = false;
        return true;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private void AtualizaDadosPlaca(String data) {

        try {
		
		/*
			JSON
            dataCB({
"Auto":"0"
,"Day":5
,"Mounth":7
,"Year":15
,"Hour":21
,"Minute":44
,"Second":40
,"temp":22.00
,"humidity":71.00
,"S1":1
,"S2":1
,"S3":1
,"S4":0
,"S5":0
,"S6":0
,"S7":0
,"S8":0
,"AgeS1HrI":1
,"AgeS1HrF":23
,"AgeS2HrI":255
,"AgeS2HrF":255
,"AgeS3HrI":255
,"AgeS3HrF":255
,"AgeS4HrI":255
,"AgeS4HrF":255
,"AgeS5HrI":255
,"AgeS5HrF":255
,"AgeS6HrI":255
,"AgeS6HrF":255
,"AgeS7HrI":255
,"AgeS7HrF":255
,"AgeS8HrI":255
,"AgeS8HrF":255
,"AgeRGBHrI":255
,"AgeRGBHrF":255
,"Red":0
,"Green":341
,"Blue":1023
,"A6":976
,"A7":902
})
       */
	   	   
            data = data.replace("dataCB(", "");
            data = data.replace(")", "");

            JSONObject jsonObj = new JSONObject( URLDecoder.decode(data, "UTF-8") );
            String sS1 = jsonObj.getString("S1");

            S1 = Integer.parseInt(jsonObj.getString("S1"));
            S2 = Integer.parseInt(jsonObj.getString("S2"));
            S3 = Integer.parseInt(jsonObj.getString("S3"));
            S4 = Integer.parseInt(jsonObj.getString("S4"));
            S5 = Integer.parseInt(jsonObj.getString("S5"));
            S6 = Integer.parseInt(jsonObj.getString("S6"));
            S7 = Integer.parseInt(jsonObj.getString("S7"));
            S8 = Integer.parseInt(jsonObj.getString("S8"));
            
            SR = Integer.parseInt(jsonObj.getString("Red"));
            SG = Integer.parseInt(jsonObj.getString("Green"));
            SB = Integer.parseInt(jsonObj.getString("Blue"));
            ModoAgendado = jsonObj.getString("Auto");
			
			Data = jsonObj.getString("Day") + "/" + jsonObj.getString("Mounth") + "/" + jsonObj.getString("Year");
			Hora = jsonObj.getString("Hour") + ":" + jsonObj.getString("Minute") + ":" + jsonObj.getString("Second");            
			
			Temperatura = jsonObj.getString("temp");
			Umidade = jsonObj.getString("humidity");
			
            S1HrI = jsonObj.getString("AgeS1HrI");
            S1HrF = jsonObj.getString("AgeS1HrF");
			S2HrI = jsonObj.getString("AgeS2HrI");
            S2HrF = jsonObj.getString("AgeS2HrF");
			S3HrI = jsonObj.getString("AgeS3HrI");
            S3HrF = jsonObj.getString("AgeS3HrF");
			S4HrI = jsonObj.getString("AgeS4HrI");
            S4HrF = jsonObj.getString("AgeS4HrF");
			S5HrI = jsonObj.getString("AgeS5HrI");
            S5HrF = jsonObj.getString("AgeS5HrF");
			S6HrI = jsonObj.getString("AgeS6HrI");
            S6HrF = jsonObj.getString("AgeS6HrF");
			S7HrI = jsonObj.getString("AgeS7HrI");
            S7HrF = jsonObj.getString("AgeS7HrF");
			S8HrI = jsonObj.getString("AgeS8HrI");
            S8HrF = jsonObj.getString("AgeS8HrF");
			SRGBHrI = jsonObj.getString("AgeRGBHrI");
            SRGBHrF = jsonObj.getString("AgeRGBHrF");
			          
            txtHorario.setText("Último Horário placa: " + Data + "  " + Hora);
			
			if(Temperatura.equals("0") && Umidade.equals("0"))
			{
				txtTemperatura.setText("");
			}
			else
			{			
				txtTemperatura.setText("Temperatura: " + Temperatura + "° - Umidade: " + Umidade + "%");
			}

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
             Toast.makeText(this, "Erro nos dados recebidos:" + ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

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
                intent.setClassName("automacaolivre.automationhomeethernet", "automacaolivre.automationhomeethernet.DefineHoraData");
                startActivityForResult(intent, 90);
                return true;
            case R.id.DeviceListActivity:
                Intent intentD = new Intent(Intent.ACTION_VIEW);
                intentD.setClassName("automacaolivre.automationhomeethernet", "automacaolivre.automationhomeethernet.DeviceListActivity");
                startActivityForResult(intentD, 90);
                return true;
            case R.id.SetupDevice:
                Intent intentS = new Intent(Intent.ACTION_VIEW);
                intentS.setClassName("automacaolivre.automationhomeethernet", "automacaolivre.automationhomeethernet.SetupDevice");
                startActivityForResult(intentS, 90);
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
            case REQUEST_SETUP_IP:
                String ipAtual = sharedPreferences.getString("IP", "");
				String comando = "http://" + ip ;						
				if(ConectarPlaca(comando, true))
				{
					IniciaTimer();		
				}					
                break;
            case REQUEST_SETUP_DEVICE:

                try {
                    AtualizaAgendamentosPlaca();
                } catch (InterruptedException e) {
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
	
		String comando = "http://" + ip + "/";		
        String data = sharedPreferences.getString("Data", "");
        String hora = sharedPreferences.getString("Hora", "");
		String dataEnvio ="?DataHoray" + data + "yz" + hora + "z|";
		
		 if (ConectarPlaca(comando + dataEnvio, true) ) {
                Toast.makeText(getApplicationContext(), "Data alterada com sucesso!", Toast.LENGTH_SHORT).show();
         }        
    }

    private void AtualizaAgendamentosPlaca() throws InterruptedException {

        try {
	
			Toast.makeText(getApplicationContext(), "Aguarde a sincronização de dados com a placa, Esse processo pode dermorar alguns segundos!", Toast.LENGTH_SHORT).show();
			
						
            sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);			
			String comando = "http://" + ip + "/";		
						
			S1HrI = sharedPreferences.getString("S1HrI", "");
            S1HrF = sharedPreferences.getString("S1HrF", "");
			String agendamento = "?AgeS1HrIy" + String.format("%02d", Integer.parseInt(S1HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S1HrF)) + "z";
			           							
            if (ConectarPlaca(comando + agendamento, true)) {
			
				S2HrI = sharedPreferences.getString("S2HrI", "");
				S2HrF = sharedPreferences.getString("S2HrF", "");
				agendamento = "?AgeS2HrIy" + String.format("%02d", Integer.parseInt(S2HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S2HrF)) + "z";
                if(ConectarPlaca(comando + agendamento, true)){				
				
					S3HrI = sharedPreferences.getString("S3HrI", "");
					S3HrF = sharedPreferences.getString("S3HrF", "");
					agendamento = "?AgeS3HrIy" + String.format("%02d", Integer.parseInt(S3HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S3HrF)) + "z";
					if(ConectarPlaca(comando + agendamento, true)){
					
						S4HrI = sharedPreferences.getString("S4HrI", "");
						S4HrF = sharedPreferences.getString("S4HrF", "");
						agendamento = "?AgeS4HrIy" + String.format("%02d", Integer.parseInt(S4HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S4HrF)) + "z";
						if(ConectarPlaca(comando + agendamento, true)){
						
							S5HrI = sharedPreferences.getString("S5HrI", "");
							S5HrF = sharedPreferences.getString("S5HrF", "");
							agendamento = "?AgeS5HrIy" + String.format("%02d", Integer.parseInt(S5HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S5HrF)) + "z";
							if(ConectarPlaca(comando + agendamento, true)){
							
								S6HrI = sharedPreferences.getString("S6HrI", "");
								S6HrF = sharedPreferences.getString("S6HrF", "");
								agendamento = "?AgeS6HrIy" + String.format("%02d", Integer.parseInt(S6HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S6HrF)) + "z";
								if(ConectarPlaca(comando + agendamento, true)){
								
									S7HrI = sharedPreferences.getString("S7HrI", "");
									S7HrF = sharedPreferences.getString("S7HrF", "");
									agendamento = "?AgeS7HrIy" + String.format("%02d", Integer.parseInt(S7HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S7HrF)) + "z";
									if(ConectarPlaca(comando + agendamento, true)){
									
										S8HrI = sharedPreferences.getString("S8HrI", "");
										S8HrF = sharedPreferences.getString("S8HrF", "");
										agendamento = "?AgeS8HrIy" + String.format("%02d", Integer.parseInt(S8HrI)) + "yz" +  String.format("%02d", Integer.parseInt(S8HrF)) + "z";
																	
										if (ConectarPlaca(comando + agendamento, true)) {
											Toast.makeText(getApplicationContext(), "Dados sincronizados com sucesso!", Toast.LENGTH_SHORT).show();
										} else {
											Toast.makeText(getApplicationContext(), "Erro durante a sincronização de dados com a placa, tente novamente!", Toast.LENGTH_SHORT).show();
										}
									}
								}
							}
						}
					}	
				}				
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro durante a atualização dos parametros na placa, tente novamente!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void EnviarComandoDigital(String S) throws IOException {

        if (ModoAgendado.equals("1")) {
            Toast.makeText(getApplicationContext(), "Modo agendado não permite acionamento de comandos, altere para o modo Manual!", Toast.LENGTH_SHORT).show();
            return;
        }
		
        //?S1L  1 ON
		//?S1D  1 OFF
        String comando = "";;
        String StatusS = "";

        if (S == "1") {
            StatusS = (S1 == 1) ? "D" : "L";
            comando = "?S1" + StatusS;					
        }
		else if (S == "2") {
            StatusS = (S2 == 1) ? "D" : "L";
            comando = "?S2" + StatusS;					
        }
		else if (S == "3") {
            StatusS = (S3 == 1) ? "D" : "L";
            comando = "?S3" + StatusS;					
        }
		else if (S == "4") {
            StatusS = (S4 == 1) ? "D" : "L";
            comando = "?S4" + StatusS;					
        }
		else if (S == "5") {
            StatusS = (S5 == 1) ? "D" : "L";
            comando = "?S5" + StatusS;					
        }
		else if (S == "6") {
            StatusS = (S6 == 1) ? "D" : "L";
            comando = "?S6" + StatusS;					
        }
		else if (S == "7") {
            StatusS = (S7 == 1) ? "D" : "L";
            comando = "?S7" + StatusS;					
        }
		else if (S == "8") {
            StatusS = (S8 == 1) ? "D" : "L";
            comando = "?S8" + StatusS;					
        }
		else
		{
			comando = "?" + S;					
		}
		        
        //int duracao = Toast.LENGTH_SHORT;
        //Toast toast = Toast.makeText(getApplicationContext(), comando, duracao);
        //toast.show();
		
		comando = "http://" + ip + "/" + comando;		
		ConectarPlaca(comando, true);
		
    }   
}



