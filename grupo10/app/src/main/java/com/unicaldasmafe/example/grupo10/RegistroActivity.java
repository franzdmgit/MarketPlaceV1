package com.unicaldasmafe.example.grupo10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private CheckBox cbh_terminos;
    private Button btn_registro_usuario;
    private EditText edt_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cbh_terminos = findViewById(R.id.chb_terminos);
        btn_registro_usuario = findViewById(R.id.btn_registro_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);

        btn_registro_usuario.setEnabled(false);
       /* cbh_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbh_terminos.isChecked()){
                    btn_registro_usuario.setEnabled(true);
                }else{
                    btn_registro_usuario.setEnabled(false);
                }
                 }
        });*/

        cbh_terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

                }
            }
        });

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