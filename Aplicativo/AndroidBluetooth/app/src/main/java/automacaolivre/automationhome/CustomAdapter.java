//http://www.webplusandroid.com/creating-listview-with-edittext-and-textwatcher-in-android/
package automacaolivre.automationhome;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Saida> {

    private LayoutInflater mInflater;
    Context context;
    private ArrayList<Saida> itens;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    int layoutResourceId;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Saida> itens) {
        super(context, layoutResourceId, itens);
        this.itens = itens;
        this.layoutResourceId = layoutResourceId;
        this.context = context;
    }

    public Saida getItem(int position) {
        return itens.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public int getCount() {
        return itens.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Saida saida = itens.get(position);
        final int Index = position;
        //if (convertView == null) {
        //    LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //    convertView = lInflater.inflate(R.layout.list_setup_item, null);
        // }

        View row = convertView;
        Holder holder = null;

        if (row == null) {
            //LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //row = lInflater.inflate(R.layout.list_setup_item, null);


            //LayoutInflater inflater = ((Activity)context).getLayoutInflater ();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layoutResourceId, parent, false);


            holder = new Holder();

            holder.Codigo = (TextView) row.findViewById(R.id.Codigo);
            holder.Descricao = (EditText) row.findViewById(R.id.Descricao);
            holder.dsHorarioInicio = (TextView) row.findViewById(R.id.dsHorarioInicio);
            holder.HorarioInicio = (EditText) row.findViewById(R.id.HorarioInicio);
            holder.dsHorarioFim = (TextView) row.findViewById(R.id.dsHorarioFim);
            holder.HorarioFim = (EditText) row.findViewById(R.id.HorarioFim);


            holder.Descricao.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    SharedPreferences.Editor editor;
                    SharedPreferences sharedPreferences;

                    SharedPreferences sharedPreferences2 = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPreferences2.edit();

                    ArrayList<Saida> itens2 = itens;
                    Log.v("MLDA", "afterTextChanged");
                    Saida saida = getItem(Index);
                    String codigo = saida.getCodigo();
                    String nome = s.toString();
                    saida.setNome(nome);
                    editor2.putString(codigo, nome).commit();

                    Log.v("MLDA", "new description = " + nome);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void afterTextChanged(Editable s) {
                    SharedPreferences.Editor editor;
                    SharedPreferences sharedPreferences;

                    SharedPreferences sharedPreferences2 = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPreferences2.edit();

                    ArrayList<Saida> itens2 = itens;
                    Log.v("MLDA", "afterTextChanged");
                    Saida saida = getItem(Index);
                    String codigo = saida.getCodigo();
                    String nome = s.toString();
                    saida.setNome(nome);
                    editor2.putString(codigo, nome).commit();

                    Log.v("MLDA", "new description = " + nome);
                }
            });
            row.setTag(holder);

        } else {
            holder = (Holder) row.getTag();
        }

        String codigo = saida.getCodigo();
        String nome = saida.getNome();
        String horarioInicio = saida.getHorarioInicio();
        String horarioFim = saida.getHorarioFim();

        holder.Codigo.setText(codigo);
        holder.Descricao.setText(nome);
        holder.dsHorarioInicio.setText("Inicio:");
        holder.HorarioInicio.setText(horarioInicio);
        holder.dsHorarioFim.setText("Fim:");
        holder.HorarioFim.setText(horarioFim);

        return row;
    }

class Holder {
    TextView Codigo;
    EditText Descricao;
    TextView dsHorarioInicio;
    EditText HorarioInicio;
    TextView dsHorarioFim;
    EditText HorarioFim;
    int position;
}
}