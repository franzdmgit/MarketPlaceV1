package com.example.damgrupo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox chb_term;
    private Button btn_nueva_cuenta;
    private Button btn_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        chb_term = findViewById(R.id.chb_term);
        btn_nueva_cuenta = findViewById(R.id.btn_nueva_cuenta);
        btn_term = findViewById(R.id.btn_term);

        btn_term.setOnClickListener(this);

        chb_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chb_term.isChecked()) {
                    btn_nueva_cuenta.setEnabled(true);
                    Toast.makeText(RegistroActivity.this, "Accept", Toast.LENGTH_SHORT).show();
                } else {
                    btn_nueva_cuenta.setEnabled(false);
                    Toast.makeText(RegistroActivity.this, "No accept", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //chb_term.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        //@Override
        //public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //btn_nueva_cuenta.setEnabled(isChecked);
        //}
        //});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_nueva_cuenta:
                Toast.makeText(this, getString(R.string.txt_nueva_cuenta), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_term:
                Intent intent = new Intent(this, TerminosActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }
}