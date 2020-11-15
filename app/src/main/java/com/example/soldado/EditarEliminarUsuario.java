package com.example.soldado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
public class EditarEliminarUsuario extends AppCompatActivity {
    private EditText ed_Cedula, ed_Nombre, ed_Apellido, ed_Correo, ed_Celular, ed_Fecha;
    private Spinner edgrado, edcargo, edbatallon, edcompania;
    private Spinner  edestado;
    private String estado,grado,compania,batallon,cargo;
    private AsyncHttpClient cliente;
    private Button btn_Actualizar, btn_Eliminar, btnBuscar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar_usuario);
        ed_Cedula = (EditText) findViewById(R.id.buscarcedula);
        ed_Nombre = (EditText) findViewById(R.id.nombreac);
        ed_Apellido = (EditText) findViewById(R.id.apellidosac);
        ed_Celular = (EditText) findViewById(R.id.celularac);
        cliente = new AsyncHttpClient();
        edgrado = (Spinner) findViewById(R.id.spinergradoac);
        edcargo = (Spinner) findViewById(R.id.cargoac);
        edbatallon = (Spinner) findViewById(R.id.spinerbatallonac);
        edcompania = (Spinner) findViewById(R.id.spinercompaniaac);
        edestado=(Spinner) findViewById(R.id.spinerestadoregistro);
        btnBuscar = (Button) findViewById(R.id.buscarsoldado);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarUsuario("https://grupob-electiva4.000webhostapp.com/Soldado/buscarsoldado.php?cedula=" + ed_Cedula.getText() + "");
            }
        });
        btn_Actualizar = (Button) findViewById(R.id.Actualizardatos);
        btn_Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutaractualizacionsoldado("https://grupob-electiva4.000webhostapp.com/Soldado/actualizardatossoldado.php");
            }
        });
        btn_Eliminar=(Button) findViewById(R.id.Eliminardatos);
        btn_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarusuario("https://grupob-electiva4.000webhostapp.com/Soldado/eliminarsoldado.php");
            }
        });


    }


    public void BuscarUsuario(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        ed_Nombre.setText(jsonObject.getString("nombres"));
                        ed_Apellido.setText(jsonObject.getString("apellidos"));
                        cargo =jsonObject.getString("cargo");
                        llenarspinnerCargo(cargo);
                        ed_Celular.setText(jsonObject.getString("telefono"));
                        grado =jsonObject.getString("grado");
                        llenarspinnerGrado(grado);
                        batallon =jsonObject.getString("batallon");
                        llenarspinnerBatallon(batallon);
                        compania =jsonObject.getString("compania");
                        llenarspinnerCompania(compania);
                        estado =jsonObject.getString("estado");
                        llenarspinnerEstado(estado);
                        llenarspinnerCompania(compania);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    public void ejecutaractualizacionsoldado(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "SOLDADO ACTUALIZADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("cedula",ed_Cedula.getText().toString());
                parametros.put("nombres", ed_Nombre.getText().toString());
                parametros.put("apellidos", ed_Apellido.getText().toString());
                parametros.put("cargo", String.valueOf(edcargo.getSelectedItem()));
                parametros.put("telefono", ed_Celular.getText().toString());
                parametros.put("grado", String.valueOf(edgrado.getSelectedItem()));
                parametros.put("batallon",String.valueOf(edbatallon.getSelectedItem()));
                parametros.put("compania", String.valueOf(edcompania.getSelectedItem()));
                parametros.put("estado", String.valueOf(edestado.getSelectedItem()));
                return parametros;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    public void eliminarusuario(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "USUARIO ELIMINADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                limpiarcampos();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("cedula", ed_Cedula.getText().toString());
                return parametros;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
    public void limpiarcampos() {
        ed_Nombre.setText("");
        ed_Apellido.setText("");
        ed_Celular.setText("");
        ed_Cedula.setText("");
    }




    private void llenarspinnerEstado(final String est) {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosEstado.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerEstado(new String(responseBody), est);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }






    public void cargarspinnerEstado(String respuesta, String ssta){
        ArrayList<SpinnerEstado> list = new ArrayList<SpinnerEstado> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=1;i<((jsonArray.length())+1); i++) {
                if (i==1){
                    SpinnerEstado p = new SpinnerEstado();
                    p.setNombreestado(ssta);
                    list.add(p);
                }
                else {
                    int aux =i;
                    aux--;
                    SpinnerEstado p = new SpinnerEstado();
                    p.setNombreestado(jsonArray.getJSONObject(aux).getString("nombreestado"));
                    list.add(p);}
            }
            ArrayAdapter<SpinnerEstado> horaArrayAdapter = new ArrayAdapter<SpinnerEstado>(this, android.R.layout.simple_dropdown_item_1line, list);
            edestado.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    private void llenarspinnerCargo(final String est) {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosCargo.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            //Se llama cuando la URL se completa correctamente
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerCargo(new String(responseBody), est);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerCargo(String respuesta, String ssta){
        ArrayList<SpinnerCargo> list = new ArrayList<SpinnerCargo> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=1;i<((jsonArray.length())+1); i++) {
                if (i==1){
                SpinnerCargo p = new SpinnerCargo();
                p.setNombrecargo(ssta);
                list.add(p);}

                else {
                    int aux =i;
                    aux--;
                    SpinnerCargo p = new SpinnerCargo();
                    p.setNombrecargo(jsonArray.getJSONObject(aux).getString("nombrecargo"));
                    list.add(p);}
            }

            ArrayAdapter<SpinnerCargo> horaArrayAdapter = new ArrayAdapter<SpinnerCargo>(this, android.R.layout.simple_dropdown_item_1line, list);
            edcargo.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarspinnerGrado(final String est) {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosGrado.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerGrado(new String(responseBody), est);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerGrado(String respuesta, String ssta){
        ArrayList<SpinnerGrado> list = new ArrayList<SpinnerGrado> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=1;i<((jsonArray.length())+1); i++) {
                if (i==1){
                SpinnerGrado p = new SpinnerGrado();
                p.setNombregrado(ssta);
                list.add(p);}
                else {
                    int aux =i;
                    aux--;
                    SpinnerGrado p = new SpinnerGrado();
                    p.setNombregrado(jsonArray.getJSONObject(aux).getString("nombregrado"));
                    list.add(p);}
            }
            ArrayAdapter<SpinnerGrado> horaArrayAdapter = new ArrayAdapter<SpinnerGrado>(this, android.R.layout.simple_dropdown_item_1line, list);
            edgrado.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void llenarspinnerBatallon(final String est) {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosBatallon.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarspinnerBatallon(new String(responseBody), est);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
    }
    public void cargarspinnerBatallon(String respuesta, String ssta){
        ArrayList<SpinnerBatallon> list = new ArrayList<SpinnerBatallon> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=1;i<((jsonArray.length())+1); i++) {
                if (i==1){
                SpinnerBatallon p = new SpinnerBatallon();
                p.setNombrebatallon(ssta);
                list.add(p);}
                else {
                    int aux =i;
                    aux--;
                    SpinnerBatallon p = new SpinnerBatallon();
                    p.setNombrebatallon(jsonArray.getJSONObject(aux).getString("nombrebatallon"));
                    list.add(p);}
            }
            ArrayAdapter<SpinnerBatallon> horaArrayAdapter = new ArrayAdapter<SpinnerBatallon>(this, android.R.layout.simple_dropdown_item_1line, list);
            edbatallon.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarspinnerCompania(final String est) {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosCompania.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerCompania(new String(responseBody), est);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerCompania(String respuesta, String ssta){
        ArrayList<SpinnerCompania> list = new ArrayList<SpinnerCompania> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=1;i<((jsonArray.length())+1); i++) {
                if (i==1){
                SpinnerCompania p = new SpinnerCompania();
                p.setNombrecompania(ssta);
                list.add(p);}
                else {
                    int aux =i;
                    aux--;
                    SpinnerCompania p = new SpinnerCompania();
                    p.setNombrecompania(jsonArray.getJSONObject(aux).getString("nombrecompania"));
                    list.add(p);}
            }
            ArrayAdapter<SpinnerCompania> horaArrayAdapter = new ArrayAdapter<SpinnerCompania>(this, android.R.layout.simple_dropdown_item_1line, list);
            edcompania.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}