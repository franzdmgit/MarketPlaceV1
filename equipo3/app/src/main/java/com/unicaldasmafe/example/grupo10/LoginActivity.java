package com.unicaldasmafe.example.grupo10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unicaldasmafe.example.grupo10.util.Constant;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private Button btn_registro;

    private EditText edt_usuario;
    private EditText edt_contrasena;

    private final int ACTIVITY_REGISTRO = 1;

    private SharedPreferences mispreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_login=findViewById(R.id.btn_login);
        btn_registro=findViewById(R.id.btn_registro);
        edt_usuario=findViewById(R.id.edt_usuario);
        edt_contrasena=findViewById(R.id.edt_contrasena);

        btn_login.setOnClickListener(this);
        btn_registro.setOnClickListener(this);

        mispreferencias = getSharedPreferences(Constant.PREFERENCE, MODE_PRIVATE);

        String usuario = mispreferencias.getString("usuario", "");
        String contrasena = mispreferencias.getString("contrasena", "");

        if (!usuario.equals("") && !contrasena.equals("")){
            toLogin(usuario, contrasena);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                String usuario = edt_usuario.getText().toString();
                String contrasena = edt_contrasena.getText().toString();
                Log.e("USUARIO", usuario);
                Log.e("CONTRASENA", contrasena);

                if (usuario.equals("admin@admin.com")&& contrasena.equals("admin")){
                    toLogin(usuario, contrasena);

                }else {
                    Toast.makeText(this, "Error iniciando sesion", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_registro:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivityForResult(intent, ACTIVITY_REGISTRO);
                break;
        }
    }

    public void toLogin(String usuario, String contrasena){
        Toast.makeText(this, "Se ha iniciado Sesion", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putString("usuario", usuario);
        editor.putString("contrasena", contrasena);

        editor.commit();

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("contrasena", contrasena);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_REGISTRO) {
            if (resultCode == Activity.RESULT_OK){



                String usuario=data.getStringExtra("correo");
                String contrasena=data.getStringExtra("contrasena");

                toLogin(usuario, contrasena);

            }
        }

    }
}