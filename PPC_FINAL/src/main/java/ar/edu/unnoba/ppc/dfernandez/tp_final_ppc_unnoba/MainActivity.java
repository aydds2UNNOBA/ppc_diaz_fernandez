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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView bienvenida1,bienvenida2,userName;
    Button botonCerrarSesion;
    //SessionManager session;
    //TODO: investigar getPreferences() en vez de getSharedPreferences(), solo necesito un archivo
    SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFERENCES, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    //RequestQueue cola = Volley.newRequestQueue(this);
    //String url = "http://ppc.edit.com.ar:8080/ppc/resources/datos/obras/-34.581727/-60.931513";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //session = new SessionManager(getApplicationContext());
        bienvenida1 = (TextView) findViewById(R.id.bienvenida1);
        bienvenida2 = (TextView) findViewById(R.id.bienvenida2);
        userName = (TextView) findViewById(R.id.userName);
        botonCerrarSesion = (Button) findViewById(R.id.botonCerrarSesion);
        botonCerrarSesion.setOnClickListener(this);
        //HashMap<String, String> user = session.getUserDetails();
        //userName.setText(user.get(SessionManager.KEY_NAME));
        userName.setText(sharedPreferences.getString("user","invitado"));
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
                                editor.clear();
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case R.id.geolocacion:
                //TODO: decidir que hacer aca, la actividad no PIDE un mapa, solo un listado
                Intent goToMap = new Intent(MainActivity.this,LocacionActivity.class);
                startActivity(goToMap);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
