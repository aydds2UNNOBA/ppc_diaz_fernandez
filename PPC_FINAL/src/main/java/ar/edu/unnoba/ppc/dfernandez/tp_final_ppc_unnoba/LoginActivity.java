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
    AlertDialogManager alert = new AlertDialogManager();
    SharedPreferences sharedPreferences;
    public static final String PREFERENCES = "preferences";

    //SessionManager session;
    /*public HashMap<String,String> getUsers(){
        return users;
    }*/
    //HashMap<String,String> users = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        ingresar = (Button) findViewById(R.id.ingresar);
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        //registrar_nuevo = (Button) findViewById(R.id.registrar_nuevo);
        ingresar.setOnClickListener(this);
        //registrar_nuevo.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        //users.put("admin","unnoba");
        // Session Manager
        /*session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ingresar:
                String username = user.getText().toString();
                String user_password = password.getText().toString();

                // Verificar si se ingreso usuario y contraseña
                if(username.trim().length() > 0 && user_password.trim().length() > 0){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user",username);
                    editor.putString("password",user_password);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    break;

                    //validar los datos ingresados
                    /*if(users.containsKey(username) && user_password.equals(users.get(username))){

                        session.createLoginSession(username, user_password);

                        // Iniciar la actividad principal
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                        break;*/

                    /*}else{
                        // no coinciden los datos
                        alert.showAlertDialog(LoginActivity.this, "Fallo el login..", "El usuario o la contraseña es incorrecto", false);
                        break;
                    }*/
                }else{
                    // el usuario no ingreso nada
                    // mostrar una alerta solicitando los datos
                    alert.showAlertDialog(LoginActivity.this, "Fallo el login..", "Por favor ingrese el usuario y la contraseña", false);
                    break;
                }

            /*case R.id.registrar_nuevo:
                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                ArrayList<String> actual_users = new ArrayList<>();
                for(Map.Entry entry: users.entrySet()){
                    actual_users.add((String)entry.getKey());
                }
                i.putStringArrayListExtra("actualUsers",actual_users);
                startActivityForResult(i,1);
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

