package com.example.soldado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuReporteGerencial extends AppCompatActivity {

    private TextView edcomision, edguardia, edhospital, edpermiso, edpresente, edterreno;
    private ImageView comisionim, guardiaim, hospitalim, permisoim, presenteim, terrenoim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reporte_gerencial);
        //PRESENTE
        edpresente=(TextView) findViewById(R.id.Presente);
        presenteim=(ImageView) findViewById(R.id.Presenteim);
        edpresente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoPresente.class));
            }
        });
        presenteim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoPresente.class));
            }
        });

        //GUARDIA
        edguardia=(TextView) findViewById(R.id.Guardia);
        guardiaim=(ImageView) findViewById(R.id.Guardiaima);
        edguardia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoGuardia.class));
            }
        });
        guardiaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoGuardia.class));
            }
        });
        //COMISION
        edcomision=(TextView) findViewById(R.id.Comision);
        comisionim=(ImageView) findViewById(R.id.Comisionim);
        edcomision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoComision.class));
            }
        });
        comisionim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoComision.class));
            }
        });


        //HOSPITAL
        edhospital=(TextView) findViewById(R.id.Hospital);
        hospitalim=(ImageView) findViewById(R.id.Hospitalim);
        edhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoHospital.class));
            }
        });
        hospitalim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoHospital.class));
            }
        });



        //PERMISO
        edpermiso=(TextView) findViewById(R.id.Permiso);
        permisoim=(ImageView) findViewById(R.id.Presenteim);
        edpermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoPermiso.class));
            }
        });
        permisoim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoPermiso.class));
            }
        });


        //TERRENO
        edterreno=(TextView) findViewById(R.id.Terreno);
        terrenoim=(ImageView) findViewById(R.id.Terrenoim);
        edterreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoTerreno.class));
            }
        });
        terrenoim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuReporteGerencial.this, ListaSoldadoTerreno.class));
            }
        });
    }
}