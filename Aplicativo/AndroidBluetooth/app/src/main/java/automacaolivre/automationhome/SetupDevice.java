
package automacaolivre.automationhome;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

import android.content.SharedPreferences;

public class SetupDevice extends Activity {

    private String NameS1 = "";
    private String NameS2 = "";
    private String NameS3 = "";
    private String NameS4 = "";
    private String NameS5 = "";
    private String NameS6 = "";
    private String NameS7 = "";
    private String NameS8 = "";
    private String NameSRGB = "";
    private Button btAlterar;

    private EditText edtS1;
    private EditText edtS2;
    private EditText edtS3;
    private EditText edtS4;
    private EditText edtS5;
    private EditText edtS6;
    private EditText edtS7;
    private EditText edtS8;
    private EditText edtSRGB;

    private EditText txtS1;
    private EditText txtS2;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_device);

        btAlterar = (Button) findViewById(R.id.btAlterar);
        txtS1 = (EditText) findViewById(R.id.edtS1);
        txtS2 = (EditText) findViewById(R.id.edtS2);


        edtS1 = (EditText) findViewById(R.id.edtS1);
        edtS2 = (EditText) findViewById(R.id.edtS2);
      /*  edtS3 = (EditText) findViewById(R.id.edtS3);
        edtS4 = (EditText) findViewById(R.id.edtS4);
        edtS5 = (EditText) findViewById(R.id.edtS5);
        edtS6 = (EditText) findViewById(R.id.edtS6);
        edtS7 = (EditText) findViewById(R.id.edtS7);
        edtS8 = (EditText) findViewById(R.id.edtS8);
        edtSRGB = (EditText) findViewById(R.id.edtSRGB);
*/

        sharedPreferences = getSharedPreferences("APP_PREFS", getBaseContext().MODE_PRIVATE);
        NameS1 = sharedPreferences.getString("S1", "S1");
        NameS2 = sharedPreferences.getString("S2", "S2");
       /* NameS3 = sharedPreferences.getString("S3", "S3");
        NameS4 = sharedPreferences.getString("S4", "S4");
        NameS5 = sharedPreferences.getString("S5", "S5");
        NameS6 = sharedPreferences.getString("S6", "S6");
        NameS7 = sharedPreferences.getString("S7", "S7");
        NameS8 = sharedPreferences.getString("S8", "S8");
        NameSRGB = sharedPreferences.getString("SRGB", "ILUMINACAO");
*/
        txtS1.setText("S1");
        txtS2.setText("S2");


        edtS1.setText(NameS1);
        edtS2.setText(NameS2);
  /*      edtS3.setText(NameS3);
        edtS4.setText(NameS4);
        edtS5.setText(NameS5);
        edtS6.setText(NameS6);
        edtS7.setText(NameS7);
        edtS8.setText(NameS8);
        edtSRGB.setText(NameSRGB);
*/
        btAlterar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                sharedPreferences = getSharedPreferences("APP_PREFS", 0);
                editor = sharedPreferences.edit();

                editor.putString("S1", edtS1.getText().toString());
                editor.putString("S2", edtS2.getText().toString());
                // editor.putString("S3", edtS3.getText().toString());
                // editor.putString("S4", edtS4.getText().toString());
                //editor.putString("S5", edtS5.getText().toString());
                // editor.putString("S6", edtS6.getText().toString());
                //  editor.putString("S7", edtS7.getText().toString());
                //   editor.putString("S8", edtS8.getText().toString());
                //   editor.putString("SRGB", edtSRGB.getText().toString());

                editor.commit();
                setResult(Activity.RESULT_OK);
                finish();

            }
        });
    }
}