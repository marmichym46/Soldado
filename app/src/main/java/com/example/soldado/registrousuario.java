package com.example.soldado;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;

import java.util.HashMap;
import java.util.Map;

public class registrousuario extends AppCompatActivity {
    private EditText ed_Nombre, ed_Apellido, ed_Nombre_Usuario, ed_Correo, ed_Contrasena;
    private AsyncHttpClient cliente;

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registorusuario);

        cliente=new AsyncHttpClient();

        ed_Nombre = (EditText) findViewById(R.id.ru_nombre);
        ed_Apellido = (EditText) findViewById(R.id.ru_apellido);
        ed_Nombre_Usuario = (EditText) findViewById(R.id.ru_nombreusuario);
        ed_Correo = (EditText) findViewById(R.id.ru_correo);
        ed_Contrasena = (EditText) findViewById(R.id.ru_password);
        Button btn_RegistroUsuario=(Button) findViewById(R.id.btn_registrar);
        btn_RegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarregistrousuario("https://grupob-electiva4.000webhostapp.com/Soldado/insertar_usuario.php");

            }
        });


    }
    public void ejecutarregistrousuario(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombres",ed_Nombre.getText().toString());
                parametros.put("apellidos",ed_Apellido.getText().toString());
                parametros.put("nombreusuario",ed_Nombre_Usuario.getText().toString());
                parametros.put("correo",ed_Correo.getText().toString());
                parametros.put("password",ed_Contrasena.getText().toString());
                return parametros;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}