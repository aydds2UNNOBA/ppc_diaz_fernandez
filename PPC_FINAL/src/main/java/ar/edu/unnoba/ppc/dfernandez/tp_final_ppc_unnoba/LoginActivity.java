package ar.edu.unnoba.ppc.dfernandez.tp_final_ppc_unnoba;
//https://developer.android.com/training/data-storage/shared-preferences#java
//https://www.tutorialspoint.com/android/android_session_management.htm
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, password;
    Button ingresar;
    //Button registrar_nuevo;
    final String default_user = "ppcadmin";
    final String default_pass = "unnoba";
    public static final String S_PREFERENCES = "preferences";
    public static final String KEY = "user";
    public static final String VALUE = "password";
    AlertDialogManager alert = new AlertDialogManager();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sp_editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        ingresar = (Button) findViewById(R.id.ingresar);
        ingresar.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(S_PREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(KEY) && sharedPreferences.contains(VALUE)) {
            //verificar si ya existe un usuario activo en la shared preference e iniciar directamente la actividad
            startMainActivity();
            finish();
        }
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        //registrar_nuevo = (Button) findViewById(R.id.registrar_nuevo);
        //registrar_nuevo.setOnClickListener(this);
        //users.put("admin","unnoba");

        // Session Manager
        /*session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }*/

    }

    public void startMainActivity(){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ingresar:
                // Verificar si se ingreso usuario y contraseña
                if(user.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0){
                    //usar un objeto User en vez de Strings simples para posible persistencia despues
                    User newLoginUser = new User(user.getText().toString(), password.getText().toString());
                    if(newLoginUser.getUsername().equals(default_user) && newLoginUser.getPassword().equals(default_pass)){

                        sp_editor = sharedPreferences.edit();
                        sp_editor.putString(KEY,newLoginUser.getUsername());
                        sp_editor.putString(VALUE,newLoginUser.getPassword());
                        sp_editor.apply(); //mejor hacerlo sin bloquear el hilo principal de la app
                        startMainActivity();
                        finish();
                        break;
                    }else{
                        // no coinciden los datos
                        alert.showAlertDialog(LoginActivity.this, "Fallo el login..", "El usuario o la contraseña es incorrecto", false);
                        break;
                    }
                }else{
                    // el usuario no ingreso nada
                    // mostrar una alerta solicitando los datos
                    alert.showAlertDialog(LoginActivity.this, "Fallo el login..", "Por favor ingrese el usuario y/o la contraseña", false);
                    break;
                }


            /*case R.id.registrar_nuevo:
                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                /*ArrayList<String> actual_users = new ArrayList<>();
                for(Map.Entry entry: users.entrySet()){
                    actual_users.add((String)entry.getKey());
                }
                i.putStringArrayListExtra("actualUsers",actual_users);
                startActivityForResult(i,1);
                startActivity(i);
                break;*/
        }

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == Activity.RESULT_OK){

                    ArrayList<String> newUser = data.getStringArrayListExtra("newUser");
                    alert.showAlertDialog(LoginActivity.this, "Exito", "Se registro correctamente el nuevo usuario"+newUser.get(0), true);
                    users.put(newUser.get(0),newUser.get(1));

                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    alert.showAlertDialog(LoginActivity.this, "Atencion!", "Se salio sin registrar un nuevo usuario", false);
                }
        }
    }*/
}

