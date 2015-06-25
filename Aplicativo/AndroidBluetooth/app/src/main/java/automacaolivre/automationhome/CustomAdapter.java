package automacaolivre.automationhome;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;


public class CustomAdapter extends BaseAdapter  {

    private LayoutInflater mInflater;
    Context context;
//private final LayoutInflater inflater;
//private final int resourceId;
private ArrayList<Saida> objects;

    public CustomAdapter(Context context, ArrayList<Saida> itens){
//public CustomAdapter(Context context, int resource, List<Saida> objects) {
    //super(context, resource, objects);
    //this.inflater = LayoutInflater.from(context);
    this.objects = itens;
    this.context = context;
    //this.resourceId = resource;

    ///super(context, 0, objects);
}

    public Saida getItem(int position)
    {
        return objects.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public int getCount()
    {
        return objects.size();
    }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

		Saida saida = getItem(position);

        //convertView = inflater.inflate(resourceId, parent, false);

       // if (convertView == null) {
        //    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_setup_item, parent, false);
       // }

     //   convertView = mInflater.inflate(R.layout.list_setup_item, null);

    if(convertView == null){
        LayoutInflater lInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = lInflater.inflate(R.layout.list_setup_item, null);
    }



        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView Codigo = (TextView) convertView.findViewById(R.id.Codigo);
        TextView Descricao = (TextView) convertView.findViewById(R.id.Descricao);
        TextView HorarioInicio = (TextView) convertView.findViewById(R.id.HorarioInicio);
        TextView HorarioFim = (TextView) convertView.findViewById(R.id.HorarioFim);

        String codigo = saida.getCodigo();
        String nome = saida.getNome();
        String horarioInicio = saida.getHorarioInicio();
        String horarioFim = saida.getHorarioFim();


        Codigo.setText(codigo);
        Descricao.setText(nome);
        HorarioInicio.setText(horarioInicio);
        HorarioFim.setText(horarioFim);

        return convertView;
}

}