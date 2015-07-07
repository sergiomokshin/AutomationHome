package automacaolivre.automationhomeethernet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;


public class IPActivity extends Activity {

    Button btAlterar;
	EditText edtIP;
	
	private String IP;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
		
    @Override
    public void onCreate(final Bundle savedInstanceState) {
	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        btAlterar = (Button) findViewById(R.id.btAlterar);
        edtIP = (EditText) findViewById(R.id.edtIP);
		
		sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
		IP = sharedPreferences.getString("IP", "192.168.0.202");
        edtIP.setText(IP);
						
		btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();

				if(edtIP.getText().toString() == "")
				{
					Toast.makeText(getApplicationContext(), "Digite o ip configurado na placa!", Toast.LENGTH_SHORT).show();
					return;				
				}
				
                IP = edtIP.getText().toString();
                
                editor.putString("IP", IP);														
                editor.commit();

                Bundle conData = new Bundle();
                conData.putString("OK", "OK");
                Intent intent = new Intent();
                intent.putExtras(conData);
                setResult(1, intent);
                finish();
            }
        });		
    }
}
