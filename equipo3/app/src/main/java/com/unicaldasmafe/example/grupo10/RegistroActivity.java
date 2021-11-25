package com.unicaldasmafe.example.grupo10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private CheckBox chb_terminos;
    private Button btn_registro_usuario;
    private EditText edt_contrasena;
    private EditText edt_correo;

    private TextView tev_terminos;

    private Activity miActividad;

    private final int ACTIVITY_TERMINOS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        chb_terminos = findViewById(R.id.chb_terminos);
        btn_registro_usuario = findViewById(R.id.btn_registro_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);
        edt_correo = findViewById(R.id.edt_correo);
        tev_terminos = findViewById(R.id.tev_terminos);

        miActividad = this;

        btn_registro_usuario.setEnabled(false);


        chb_terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_registro_usuario.setEnabled(isChecked);
            }
        });


        btn_registro_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrasena = edt_contrasena.getText().toString();
                if (contrasena.length() < 8 && !isValidPassword(contrasena)) {
                    Toast.makeText(RegistroActivity.this, "contrasena no valida", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistroActivity.this, "contrasena  valida", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("correo", edt_correo.getText().toString());
                resultIntent.putExtra("contrasena", edt_correo.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);

                finish();

                }
            }
        });


        tev_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegistroActivity.this, TerminosActivity.class)
                Intent intent = new Intent(miActividad, TerminosActivity.class);
                startActivityForResult(intent, ACTIVITY_TERMINOS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_TERMINOS) {
            if (resultCode == Activity.RESULT_OK) {
                String estado = data.getStringExtra("ESTADO");
                Toast.makeText(miActividad, "Acepto terminos", Toast.LENGTH_SHORT).show();
                chb_terminos.setChecked(true);
            } else {
                chb_terminos.setChecked(false);
                Toast.makeText(miActividad, "No acepto terminos", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}