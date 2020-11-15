package com.example.soldado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private EditText et_usuario, et_contra;
    private Button btn_Logear;
    private String usuario, password;
    //El AsyncHttpClient se puede utilizar para realizar solicitudes HTTP GET, POST, PUT y DELETE
    // asincrónicas en sus aplicaciones de Android. Las solicitudes se pueden realizar con parámetros
    // adicionales pasando una RequestParamsinstancia
    private AsyncHttpClient usuario_clien;
    private Button btn_RegistroUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_Logear = (Button) findViewById(R.id.btn_autenticar);
        et_usuario = (EditText) findViewById(R.id.et_correo);
        et_contra = (EditText) findViewById(R.id.et_password);


        btn_RegistroUsuario = (Button) findViewById(R.id.btn_CrearCuenta);
        btn_RegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, registrousuario.class));


            }
        });

        usuario_clien = new AsyncHttpClient();
        botonLoguin();
        recuperarPreferencias(); }

    private void botonLoguin() {
        btn_Logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SI LOS CAMPOS DE LA ACTIVIDAD LOGIN SE ENCUENTRAN VACIO, MOSTRARA UN MENSAJE "HAY CAMPOS VACIOS"
                if (et_usuario.getText().toString().isEmpty() || et_contra.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Hay Campos En Blanco!!", Toast.LENGTH_SHORT).show();
                } else {
                     usuario = et_usuario.getText().toString().replace(" ", "%20");
                     password = et_contra.getText().toString().replace(" ", "%20");
                     String url = "https://grupob-electiva4.000webhostapp.com/Soldado/logear.php?nombreusuario=" + usuario + "&password=" + password;
                    usuario_clien.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                            Toast.makeText(MainActivity.this, "prueba entro", Toast.LENGTH_SHORT).show();

                            if (statusCode == 200) {
                                guardarPreferencias();
                                String respuesta = new String(responseBody);
                                finish();
                                if (respuesta.equalsIgnoreCase("null")) {
                                    Toast.makeText(MainActivity.this, "Error De Usuario y/o Contraseña!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    try {
                                        JSONObject jsonObj = new JSONObject(respuesta);
                                        Logear_usuario user = new Logear_usuario();
                                        user.setId(jsonObj.getInt("id_usuario"));
                                        user.setNombre_usuario(jsonObj.getString("nombreusuario"));
                                        user.setPassword(jsonObj.getString("password"));
                                        user.setId_rol(jsonObj.getInt("rol"));
                                        Intent i = null;
                                        switch (user.getId_rol()) {
                                            case 1:
                                                user.setId_rol(1);
                                                i = new Intent(MainActivity.this, MenuPrincipal.class);
                                                break;
                                            default:
                                                Toast.makeText(getApplicationContext(), "PROBLEMA CON EL ROL", Toast.LENGTH_SHORT).show();
                                                break; }
                                        startActivity(i);
                                    } catch (Exception e) {
                                        e.printStackTrace(); } } } }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(MainActivity.this, "Error Desconocido. Intentelo De Nuevo!!", Toast.LENGTH_SHORT).show();
                            et_usuario.setText("");
                            et_contra.setText("");
                        }


                    });
                }

            }

        });

    }


    public void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenceslogil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("usuario", usuario);
        editor.putString("password", password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    public void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferencialogin", Context.MODE_PRIVATE);
        et_usuario.setText(preferences.getString("usuario", usuario));
        et_contra.setText(preferences.getString("password", password));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItrmSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent regresar = new Intent(this, Configuracion.class);
                startActivity(regresar);
                return  true;
            default:
                return true;
        }

    }
}