package com.example.soldado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaSoldadoGuardia extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>{

    RecyclerView recyclerSoldado;
    ArrayList<LisSoldado> listasoldado;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_soldado_guardia);

        listasoldado=new ArrayList<>();
        recyclerSoldado = (RecyclerView) findViewById(R.id.RecyclerGuardia);
        recyclerSoldado.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerSoldado.setHasFixedSize(true);

        request= Volley.newRequestQueue(getApplicationContext());

        cargarservice();


    }

    public void cargarservice(){
        String url="https://grupob-electiva4.000webhostapp.com/Soldado/ListaEstado/lissoldadoGuardia.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se puede conectar " +error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        LisSoldado centrousu=null;

        JSONArray json=response.optJSONArray("soldado");
        try {
            for (int i=0;i<json.length();i++){
                centrousu=new LisSoldado();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                centrousu.setCedula(jsonObject.optString("cedula"));
                centrousu.setNombres(jsonObject.optString("nombres"));
                centrousu.setApellidos(jsonObject.optString("apellidos"));
                centrousu.setCargo(jsonObject.optString("cargo"));
                centrousu.setTelefono(jsonObject.optString("telefono"));
                centrousu.setFecha(jsonObject.optString("fecha"));
                centrousu.setGrado(jsonObject.optString("grado"));
                centrousu.setBatallon(jsonObject.optString("batallon"));
                centrousu.setCompania(jsonObject.optString("compania"));


                listasoldado.add(centrousu);
            }
            SoldadoAdapter adapter=new SoldadoAdapter(listasoldado, getApplicationContext());
            recyclerSoldado.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "No se encuentra registro de la tabla Soldado " +
                    " "+response, Toast.LENGTH_LONG).show();
        }

    }

}