package com.example.soldado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class RegistrarSoldado extends AppCompatActivity {
    private EditText fecha;
    private int dd, mm, aa;
    private EditText ed_Cedula, ed_Nombre, ed_Apellido, ed_Correo, ed_Celular, ed_Fecha;
    private Spinner spinergrado, spinercargo, spinerbatallon, spinercompania, spinerestado;
    private AsyncHttpClient cliente;
    private Button btn_ReInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_soldado);
        ed_Cedula=(EditText) findViewById(R.id.cedularegistro);
        ed_Nombre=(EditText) findViewById(R.id.nombreregistro);
        ed_Apellido=(EditText) findViewById(R.id.apellidosregistro);
        ed_Celular=(EditText) findViewById(R.id.celularregistro);
        fecha = (EditText) findViewById(R.id.txtCa);
        cliente=new AsyncHttpClient();
        spinergrado=(Spinner) findViewById(R.id.spinergradoregistro);
        spinercargo=(Spinner) findViewById(R.id.cargoregistro);
        spinerbatallon=(Spinner) findViewById(R.id.spinerbatallonregistro);
        spinercompania=(Spinner) findViewById(R.id.spinercompaniaregistro);
        spinerestado=(Spinner) findViewById(R.id.spinerestadoregistro);
        llenarspinnerCargo();
        llenarspinnerGrado();
        llenarspinnerBatallon();
        llenarspinnerCompania();
        llenarspinnerEstado();
        btn_ReInformacion=(Button) findViewById(R.id.btn_registrar);
        btn_ReInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarregistrosoldado("https://grupob-electiva4.000webhostapp.com/Soldado/insertar_soldado.php");
            }
        });
    }
    public void ejecutarregistrosoldado(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "SOLDADO REGISTRADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            //Para obtener los datos ingresados en cada Editext y Spinner y guardarlos mediante el POST
            //a cada campos de la tabla soldado
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("cedula",ed_Cedula.getText().toString());
                parametros.put("nombres",ed_Nombre.getText().toString());
                parametros.put("apellidos",ed_Apellido.getText().toString());
                parametros.put("cargo", String.valueOf(spinercargo.getSelectedItem()));
                parametros.put("telefono",ed_Celular.getText().toString());
                parametros.put("fecha",fecha.getText().toString());
                parametros.put("grado", String.valueOf(spinergrado.getSelectedItem()));
                parametros.put("batallon", String.valueOf(spinerbatallon.getSelectedItem()));
                parametros.put("compania", String.valueOf(spinercompania.getSelectedItem()));
                parametros.put("estado", String.valueOf(spinerestado.getSelectedItem()));
                return parametros;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void llenarspinnerCargo() {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosCargo.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            //Se llama cuando la URL se completa correctamente
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerCargo(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerCargo(String respuesta){
        ArrayList<SpinnerCargo> list = new ArrayList<SpinnerCargo> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=0;i<jsonArray.length(); i++) {
                SpinnerCargo p = new SpinnerCargo();
                p.setNombrecargo(jsonArray.getJSONObject(i).getString("nombrecargo"));
                list.add(p);
            }
            ArrayAdapter<SpinnerCargo> horaArrayAdapter = new ArrayAdapter<SpinnerCargo>(this, android.R.layout.simple_dropdown_item_1line, list);
            spinercargo.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarspinnerGrado() {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosGrado.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerGrado(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerGrado(String respuesta){
        ArrayList<SpinnerGrado> list = new ArrayList<SpinnerGrado> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=0;i<jsonArray.length(); i++) {
                SpinnerGrado p = new SpinnerGrado();
                p.setNombregrado(jsonArray.getJSONObject(i).getString("nombregrado"));
                list.add(p);
            }
            ArrayAdapter<SpinnerGrado> horaArrayAdapter = new ArrayAdapter<SpinnerGrado>(this, android.R.layout.simple_dropdown_item_1line, list);
            spinergrado.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void llenarspinnerBatallon() {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosBatallon.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarspinnerBatallon(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
    }
    public void cargarspinnerBatallon(String respuesta){
        ArrayList<SpinnerBatallon> list = new ArrayList<SpinnerBatallon> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=0;i<jsonArray.length(); i++) {
                SpinnerBatallon p = new SpinnerBatallon();
                p.setNombrebatallon(jsonArray.getJSONObject(i).getString("nombrebatallon"));
                list.add(p);
            }
            ArrayAdapter<SpinnerBatallon> horaArrayAdapter = new ArrayAdapter<SpinnerBatallon>(this, android.R.layout.simple_dropdown_item_1line, list);
            spinerbatallon.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarspinnerCompania() {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosCompania.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerCompania(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerCompania(String respuesta){
        ArrayList<SpinnerCompania> list = new ArrayList<SpinnerCompania> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=0;i<jsonArray.length(); i++) {
                SpinnerCompania p = new SpinnerCompania();
                p.setNombrecompania(jsonArray.getJSONObject(i).getString("nombrecompania"));
                list.add(p);
            }
            ArrayAdapter<SpinnerCompania> horaArrayAdapter = new ArrayAdapter<SpinnerCompania>(this, android.R.layout.simple_dropdown_item_1line, list);
            spinercompania.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarspinnerEstado() {
        String URL = "https://grupob-electiva4.000webhostapp.com/Soldado/ObtenerDatosSpinner/obtenerdatosEstado.php";
        cliente.post(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarspinnerEstado(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarspinnerEstado(String respuesta){
        ArrayList<SpinnerEstado> list = new ArrayList<SpinnerEstado> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=0;i<jsonArray.length(); i++) {
                SpinnerEstado p = new SpinnerEstado();
                p.setNombreestado(jsonArray.getJSONObject(i).getString("nombreestado"));
                list.add(p);
            }
            ArrayAdapter<SpinnerEstado> horaArrayAdapter = new ArrayAdapter<SpinnerEstado>(this, android.R.layout.simple_dropdown_item_1line, list);
            spinerestado.setAdapter(horaArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse("2020/10/02");
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        dd = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        aa = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int daysOfMonth) {
                fecha.setText(daysOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }
                , dd, mm, aa);
        datePickerDialog.show();




    }


}
