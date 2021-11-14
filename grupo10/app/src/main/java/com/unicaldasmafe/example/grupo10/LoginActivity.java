package com.unicaldasmafe.example.grupo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private Button btn_registro;

    private EditText edt_usuario;
    private EditText edt_contrasena;

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
                    Toast.makeText(this, "Se ha iniciado Sesion", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, MenuActivity.class);
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("contrasena", contrasena);
                    startActivity(intent);

                }else {
                    Toast.makeText(this, "Error iniciando sesion", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_registro:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
        }
    }
}