package aybm.concesionario;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import aybm.concesionario.entidades.Coche;

public class Adapter extends ArrayAdapter {
    private Activity context;
    private List<Coche> coches;
    public Adapter(@NonNull Activity context, int resource,  List<Coche> coches) {
        super(context, resource, coches);
        this.coches = coches;
        this.context = context;
    }
    private static class ViewHolder{
        public TextView TxtCoche;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder;
        if (row == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            row = layoutInflater.inflate(R.layout.single_item_listacoches, null);
            viewHolder=new ViewHolder();
            viewHolder.TxtCoche=row.findViewById(R.id.single_item_tv_marca);
            row.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)row.getTag();
        }
        viewHolder.TxtCoche.setText(coches.get(position).getMarca()+" "+coches.get(position).getModelo());
        return row;
    }

}
