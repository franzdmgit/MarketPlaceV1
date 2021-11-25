package com.unicaldasmafe.example.grupo10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_jugar;
    private Button btn_perfil;
    private Button btn_config;
    private Button btn_acerca;

    private Activity miActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miActividad = this;

        btn_jugar =findViewById(R.id.btn_jugar);
        btn_perfil =findViewById(R.id.btn_perfil);
        btn_config =findViewById(R.id.btn_config);
        btn_acerca =findViewById(R.id.btn_acerca);



        btn_jugar.setOnClickListener(this);
        btn_perfil.setOnClickListener(this);
        btn_config.setOnClickListener(this);
        btn_acerca.setOnClickListener(this);




    }

    public void clickjugar(View v) {
        Log.e("click_main", "le e dado al boton jugar ");
        Toast.makeText(this, getString(R.string.txt_jugar), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btn_jugar:
              Toast.makeText( this, getString(R.string.txt_jugar), Toast.LENGTH_SHORT).show();
              break;
          case R.id.btn_perfil:
              Toast.makeText( this, getString(R.string.txt_perfil), Toast.LENGTH_SHORT).show();
              break;
          case R.id.btn_config:
              Toast.makeText( this, getString(R.string.txt_ajustes), Toast.LENGTH_SHORT).show();
              break;
          case R.id.btn_acerca:
              Toast.makeText( this, getString(R.string.txt_acercade), Toast.LENGTH_SHORT).show();
              break;
      }
    }
}