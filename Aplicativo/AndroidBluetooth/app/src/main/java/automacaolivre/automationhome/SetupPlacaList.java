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

        String NameSaida1 = sharedPreferences.getString("NAME_SAIDA1", "Saida 1");
        String NameSaida2 = sharedPreferences.getString("NAME_SAIDA2", "Saida 2");
        String NameSaida3 = sharedPreferences.getString("NAME_SAIDA3", "Saida 3");
        String NameSaida4 = sharedPreferences.getString("NAME_SAIDA4", "Saida 4");
        String NameSaida5 = sharedPreferences.getString("NAME_SAIDA5", "Saida 5");
        String NameSaida6 = sharedPreferences.getString("NAME_SAIDA6", "Saida 6");
        String NameSaida7 = sharedPreferences.getString("NAME_SAIDA7", "Saida 7");
        String NameSaida8 = sharedPreferences.getString("NAME_SAIDA8", "Saida 8");
        String NameSaidaRGB = sharedPreferences.getString("NAME_SAIDARGB", "ILUMINACAO");
        String ValueSaida1HrI = sharedPreferences.getString("ValueSaida1HrI", "0");
        String ValueSaida1HrF = sharedPreferences.getString("ValueSaida1HrF", "0");
        String ValueSaida2HrI = sharedPreferences.getString("ValueSaida2HrI", "0");
        String ValueSaida2HrF = sharedPreferences.getString("ValueSaida2HrF", "0");
        String ValueSaida3HrI = sharedPreferences.getString("ValueSaida3HrI", "0");
        String ValueSaida3HrF = sharedPreferences.getString("ValueSaida3HrF", "0");
        String ValueSaida4HrI = sharedPreferences.getString("ValueSaida4HrI", "0");
        String ValueSaida4HrF = sharedPreferences.getString("ValueSaida4HrF", "0");
        String ValueSaida5HrI = sharedPreferences.getString("ValueSaida5HrI", "0");
        String ValueSaida5HrF = sharedPreferences.getString("ValueSaida5HrF", "0");
        String ValueSaida6HrI = sharedPreferences.getString("ValueSaida6HrI", "0");
        String ValueSaida6HrF = sharedPreferences.getString("ValueSaida6HrF", "0");
        String ValueSaida7HrI = sharedPreferences.getString("ValueSaida7HrI", "0");
        String ValueSaida7HrF = sharedPreferences.getString("ValueSaida7HrF", "0");
        String ValueSaida8HrI = sharedPreferences.getString("ValueSaida8HrI", "0");
        String ValueSaida8HrF = sharedPreferences.getString("ValueSaida8HrF", "0");
        String ValueRGBHrI = sharedPreferences.getString("ValueRGBHrI", "0");
        String ValueRGBHrF = sharedPreferences.getString("ValueRGBHrF", "0");


        saidaList = new ArrayList<Saida>();
        saidaList.add(new Saida("S1", NameSaida1, ValueSaida1HrI, ValueSaida1HrF));
        saidaList.add(new Saida("S2", NameSaida2, ValueSaida2HrI, ValueSaida2HrF));
        saidaList.add(new Saida("S3", NameSaida3, ValueSaida3HrI, ValueSaida3HrF));
        saidaList.add(new Saida("S4", NameSaida4, ValueSaida4HrI, ValueSaida4HrF));
        saidaList.add(new Saida("S5", NameSaida5, ValueSaida5HrI, ValueSaida5HrF));
        saidaList.add(new Saida("S6", NameSaida6, ValueSaida6HrI, ValueSaida6HrF));
        saidaList.add(new Saida("S7", NameSaida7, ValueSaida7HrI, ValueSaida7HrF));
        saidaList.add(new Saida("S8", NameSaida8, ValueSaida8HrI, ValueSaida8HrF));
        saidaList.add(new Saida("RGB", NameSaidaRGB, ValueRGBHrI, ValueRGBHrF));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //	super.onSaveInstanceState(outState);
        //outState.putSerializable(KEY, (ArrayList<Saida>) saidaList);

    }

}