package automacaolivre.automationhome;

import java.util.ArrayList;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
public class SetupPlacaList extends Activity {

    private ArrayList<Saida> saidaList;
    private final static String KEY = "SAVED_STATE_KEY";
    public ListView lv;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    private CustomAdapter m_adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_setup);

        LoadList();
        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new CustomAdapter(this, saidaList));

    }

    private void LoadList() {

        sharedPreferences = getSharedPreferences("APP_PREFS", 0);

        String NameSaida1 = sharedPreferences.getString("SAIDA1", "Saida 1");
        String NameSaida2 = sharedPreferences.getString("SAIDA2", "Saida 2");
        String NameSaida3 = sharedPreferences.getString("SAIDA3", "Saida 3");
        String NameSaida4 = sharedPreferences.getString("SAIDA4", "Saida 4");
        String NameSaida5 = sharedPreferences.getString("SAIDA5", "Saida 5");
        String NameSaida6 = sharedPreferences.getString("SAIDA6", "Saida 6");
        String NameSaida7 = sharedPreferences.getString("SAIDA7", "Saida 7");
        String NameSaida8 = sharedPreferences.getString("SAIDA8", "Saida 8");
        String NameSaidaRGB = sharedPreferences.getString("SAIDARGB", "ILUMINACAO");
        String SAIDA1HrI = sharedPreferences.getString("SAIDA1HrI", "0");
        String SAIDA1HrF = sharedPreferences.getString("SAIDA1HrF", "0");
        String SAIDA2HrI = sharedPreferences.getString("SAIDA2HrI", "0");
        String SAIDA2HrF = sharedPreferences.getString("SAIDA2HrF", "0");
        String SAIDA3HrI = sharedPreferences.getString("SAIDA3HrI", "0");
        String SAIDA3HrF = sharedPreferences.getString("SAIDA3HrF", "0");
        String SAIDA4HrI = sharedPreferences.getString("SAIDA4HrI", "0");
        String SAIDA4HrF = sharedPreferences.getString("SAIDA4HrF", "0");
        String SAIDA5HrI = sharedPreferences.getString("SAIDA5HrI", "0");
        String SAIDA5HrF = sharedPreferences.getString("SAIDA5HrF", "0");
        String SAIDA6HrI = sharedPreferences.getString("SAIDA6HrI", "0");
        String SAIDA6HrF = sharedPreferences.getString("SAIDA6HrF", "0");
        String SAIDA7HrI = sharedPreferences.getString("SAIDA7HrI", "0");
        String SAIDA7HrF = sharedPreferences.getString("SAIDA7HrF", "0");
        String SAIDA8HrI = sharedPreferences.getString("SAIDA8HrI", "0");
        String SAIDA8HrF = sharedPreferences.getString("SAIDA8HrF", "0");
        String ValueRGBHrI = sharedPreferences.getString("ValueRGBHrI", "0");
        String ValueRGBHrF = sharedPreferences.getString("ValueRGBHrF", "0");


        saidaList = new ArrayList<Saida>();
        saidaList.add(new Saida("S1", NameSaida1, SAIDA1HrI, SAIDA1HrF));
        saidaList.add(new Saida("S2", NameSaida2, SAIDA2HrI, SAIDA2HrF));
        saidaList.add(new Saida("S3", NameSaida3, SAIDA3HrI, SAIDA3HrF));
        saidaList.add(new Saida("S4", NameSaida4, SAIDA4HrI, SAIDA4HrF));
        saidaList.add(new Saida("S5", NameSaida5, SAIDA5HrI, SAIDA5HrF));
        saidaList.add(new Saida("S6", NameSaida6, SAIDA6HrI, SAIDA6HrF));
        saidaList.add(new Saida("S7", NameSaida7, SAIDA7HrI, SAIDA7HrF));
        saidaList.add(new Saida("S8", NameSaida8, SAIDA8HrI, SAIDA8HrF));
        saidaList.add(new Saida("RGB", NameSaidaRGB, ValueRGBHrI, ValueRGBHrF));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //	super.onSaveInstanceState(outState);
        //outState.putSerializable(KEY, (ArrayList<Saida>) saidaList);

    }

}