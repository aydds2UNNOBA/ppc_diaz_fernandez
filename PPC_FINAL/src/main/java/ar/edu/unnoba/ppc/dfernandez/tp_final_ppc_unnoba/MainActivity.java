package ar.edu.unnoba.ppc.dfernandez.tp_final_ppc_unnoba;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView bienvenida1,bienvenida2,userName;
    Button botonCerrarSesion,listarObras;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    RequestQueue cola;
    String url = "http://ppc.edit.com.ar:8080/resources/datos/obras/-34.581727/-60.931513";
    List<Obra> obras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bienvenida1 = (TextView) findViewById(R.id.bienvenida1);
        bienvenida2 = (TextView) findViewById(R.id.bienvenida2);
        userName = (TextView) findViewById(R.id.userName);
        botonCerrarSesion = (Button) findViewById(R.id.botonCerrarSesion);
        botonCerrarSesion.setOnClickListener(this);
        listarObras = (Button) findViewById(R.id.listarObras);
        listarObras.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(LoginActivity.S_PREFERENCES, Context.MODE_PRIVATE);
        userName.setText(sharedPreferences.getString("user","invitado"));
        cola = Volley.newRequestQueue(this);
        gson = new Gson();
        cargar_obras();

    }

    private void cargar_obras(){
        cola = Volley.newRequestQueue(this);
        JsonArrayRequest json_request = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        llenar_lista(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR: hubo un error al conectar con el Web Service en [ "+url+" ]");
            }
        });

        //Intent goToMap = new Intent(MainActivity.this,LocacionActivity.class);
        //startActivity(goToMap);
        cola.add(json_request);
    }
    private void llenar_lista(JSONArray source){
        obras = Arrays.asList(gson.fromJson(source.toString(),Obra[].class));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.botonCerrarSesion:
                //session.logoutUser();
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Cerrar Sesión")
                        .setMessage("¿Esta seguro de que desea cerrar la sesión?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case R.id.listarObras:
                //TODO: decidir que hacer aca, la actividad no PIDE un mapa, solo un listado
                
                break;

        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
