package com.example.soldado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SoldadoAdapter extends RecyclerView.Adapter<SoldadoAdapter.SoldadosHolder> {
    List<LisSoldado> listasoldado;
    public SoldadoAdapter(List<LisSoldado> listasoldado, Context  applicationContext) {
        this.listasoldado = listasoldado;
    }
    @NonNull
    @Override
    public SoldadoAdapter.SoldadosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.listarsoldado,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new SoldadoAdapter.SoldadosHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull SoldadoAdapter.SoldadosHolder holder, int position) {
        holder.txtcedula.setText(listasoldado.get(position).getCedula().toString());
        holder.txtnombres.setText(listasoldado.get(position).getNombres().toString());
        holder.txtapellidos.setText(listasoldado.get(position).getApellidos().toString());
        holder.txtcargo.setText(listasoldado.get(position).getCargo().toString());
        holder.txtcelular.setText(listasoldado.get(position).getTelefono().toString());
        holder.txtfecha.setText(listasoldado.get(position).getFecha().toString());
        holder.txtgrado.setText(listasoldado.get(position).getGrado().toString());
        holder.txtbatallon.setText(listasoldado.get(position).getBatallon().toString());
        holder.txtcompania.setText(listasoldado.get(position).getCompania().toString());
    }
    @Override
    public int getItemCount() {
        return listasoldado.size();
    }


    //CREAR VARIABLES Y ENLAZARLO CON EL ID
    public class SoldadosHolder extends RecyclerView.ViewHolder {

        private TextView txtcedula, txtnombres, txtapellidos, txtcargo, txtcelular, txtfecha, txtgrado, txtbatallon, txtcompania;


        public SoldadosHolder(@NonNull View itemView) {
            super(itemView);

            txtcedula=(TextView) itemView.findViewById(R.id.cedulalis);
            txtnombres=(TextView) itemView.findViewById(R.id.nombreslis);
            txtapellidos=(TextView) itemView.findViewById(R.id.apellidoslis);
            txtcargo=(TextView) itemView.findViewById(R.id.cargolis);
            txtcelular=(TextView) itemView.findViewById(R.id.telefonolis);
            txtfecha=(TextView) itemView.findViewById(R.id.fechalis);
            txtgrado=(TextView) itemView.findViewById(R.id.gradolis);
            txtbatallon=(TextView) itemView.findViewById(R.id.batallonlis);
            txtcompania=(TextView) itemView.findViewById(R.id.companialis);

        }
    }

}
