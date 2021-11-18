package com.example.damgrupo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_entrar;
    private Button btn_nueva_cuenta;

    private EditText edt_usuario;
    private EditText edt_contrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_entrar = findViewById(R.id.btn_entrar);
        btn_nueva_cuenta = findViewById(R.id.btn_nueva_cuenta);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);

        btn_entrar.setOnClickListener(this);
        btn_nueva_cuenta.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_entrar:
                String usuario = edt_usuario.getText().toString();
                String contrasena = edt_contrasena.getText().toString();

                Log.e("USER", usuario);
                Log.e("Password", contrasena);

                if (usuario.equals("admin@clothes.co") && contrasena.equals("admin")) {
                    Toast.makeText(this, "You logged in", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, MenuActivity.class);
                    intent.putExtra("USER", usuario);
                    intent.putExtra("contrasena", contrasena);
                    intent.putExtra("number", 1);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "You did not log in", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.btn_nueva_cuenta:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;

        }
    }
}