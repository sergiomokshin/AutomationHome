//http://www.webplusandroid.com/creating-listview-with-edittext-and-textwatcher-in-android/
package automacaolivre.automationhome;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextWatcher;
import android.text.Editable;


public class CustomAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    Context context;
    private ArrayList<Saida> itens;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;


    public CustomAdapter(Context context, ArrayList<Saida> itens) {
        this.itens = itens;
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

        Saida saida = getItem(position);
        if (convertView == null) {
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = lInflater.inflate(R.layout.list_setup_item, null);
        }

       // ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView Codigo = (TextView) convertView.findViewById(R.id.Codigo);
        EditText Descricao = (EditText) convertView.findViewById(R.id.Descricao);
        TextView dsHorarioInicio = (TextView) convertView.findViewById(R.id.dsHorarioInicio);
		EditText HorarioInicio = (EditText) convertView.findViewById(R.id.HorarioInicio);
		TextView dsHorarioFim = (TextView) convertView.findViewById(R.id.dsHorarioFim);
		EditText HorarioFim = (EditText) convertView.findViewById(R.id.HorarioFim);
        
        String codigo = saida.getCodigo();
        String nome = saida.getNome();
        String horarioInicio = saida.getHorarioInicio();
        String horarioFim = saida.getHorarioFim();

        Codigo.setText(codigo);
        Descricao.setText(nome);
		dsHorarioInicio.setText("Inic�o:");
        HorarioInicio.setText(horarioInicio);
		dsHorarioFim.setText("Fim:");
        HorarioFim.setText(horarioFim);
        final int Index = position;
		
		//final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);		
        final SharedPreferences sharedPreferences2 = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = sharedPreferences2.edit();
		Descricao.addTextChangedListener(new TextWatcher() {
 
                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
 
                }
 
                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                        int arg3) {
                    // TODO Auto-generated method stub
 
                }
 
                @Override
                public void afterTextChanged(Editable arg0) {
                    
					Saida saida = getItem(Index);
					
					saida.setNome(arg0.toString());
                    editor2.putString(saida.getCodigo(), arg0.toString()).commit();
                    					
					// TODO Auto-generated method stub
                    //arrTemp[holder.ref] = arg0.toString();
					//prefs.edit().putString("autoSave", s.toString()).commit();
																		
                }
            });
 
 
		

        return convertView;
    }

}