package com.example.soldado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    private TextView txtadd, txtupdatedelete, txtlist, txtconfiguration;
    private ImageView addim, updatedeleteim, listim, configurationim;
    private Button btnCerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        txtadd=(TextView) findViewById(R.id.Agregar);
        addim=(ImageView) findViewById(R.id.Agregarim);
        txtupdatedelete=(TextView) findViewById(R.id.ActualizarEliminar);
        updatedeleteim=(ImageView) findViewById(R.id.ActualizarEliminarima);
        txtlist=(TextView) findViewById(R.id.Lista);
        listim=(ImageView) findViewById(R.id.Listaim);
        txtconfiguration=(TextView) findViewById(R.id.Configuracion);
        configurationim=(ImageView) findViewById(R.id.Configuracionim);
btnCerrarSesion=(Button) findViewById(R.id.cerrarsesionbtn);
btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SharedPreferences preferences=getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
});
        txtadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, RegistrarSoldado.class));
            }
        });
        addim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, RegistrarSoldado.class));
            }
        });
        txtupdatedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, EditarEliminarUsuario.class));
            }
        });
        updatedeleteim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, EditarEliminarUsuario.class));
            }
        });
        txtlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, MenuReporteGerencial.class));
            }
        });
        listim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, MenuReporteGerencial.class));
            }
        });
        txtconfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Configuracion.class));
            }
        });
        configurationim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Configuracion.class));
            }
        });

    }
}