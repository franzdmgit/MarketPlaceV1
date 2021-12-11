package com.unicaldasmafe.example.grupo10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unicaldasmafe.example.grupo10.database.UserDatabase;
import com.unicaldasmafe.example.grupo10.database.model.User;
import com.unicaldasmafe.example.grupo10.util.Utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private CheckBox chb_terminos;
    private Button btn_registro_usuario;
    private EditText edt_contrasena;
    private EditText edt_correo;
    private EditText edt_nombre;
    private EditText edt_apellido;

    private TextView tev_terminos;

    private Activity miActividad;
    private UserDatabase database;
    private FirebaseAuth mAuth;
    private final int ACTIVITY_TERMINOS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        chb_terminos = findViewById(R.id.chb_terminos);
        btn_registro_usuario = findViewById(R.id.btn_registro_usuario);
        edt_nombre = findViewById(R.id.edt_nombre);
        edt_apellido =  findViewById(R.id.edt_apellido);
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
                String nombre=edt_nombre.getText().toString();
                String apellido=edt_apellido.getText().toString();
                String correo=edt_correo.getText().toString();
                String contrasena = edt_contrasena.getText().toString();

                if (contrasena.length() < 8 && !isValidPassword(contrasena)) {
                    Toast.makeText(RegistroActivity.this, "contrasena no valida", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistroActivity.this, "contrasena  valida", Toast.LENGTH_SHORT).show();

                    //Intent resultIntent = new Intent();
                    //resultIntent.putExtra("correo", edt_correo.getText().toString());
                    //resultIntent.putExtra("contrasena", edt_contrasena.getText().toString());
                    //setResult(Activity.RESULT_OK, resultIntent);


                    //proceso con base de datos
                    //User user =new User(nombre,apellido,correo, Utilidades.md5(contrasena));
                    //long l = database.getUserDao().insertUser(user);
                    //finish();
                    //registrarUsuarioFirebase(correo,contrasena);
                    //finish();
                    registrarUsuarioFirestore(nombre,apellido,correo,Utilidades.md5(contrasena));
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

        database = UserDatabase.getInstance(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }
    public void registrarUsuarioFirestore(String nombre ,String apellido , String correo, String contrasena){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nombre", nombre);
        usuario.put("apellido", apellido);
        usuario.put("correo",correo);
        usuario.put("contrasena",contrasena);

// Add a new document with a generated ID
        /*
        db.collection("usuarios")
                //.add(usuario)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });*/
        db.collection("usuarios")
                .document(correo)
                .set(usuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                        registrarUsuarioFirebase(correo,contrasena);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

    }
    public void registrarUsuarioFirebase(String correo, String contrasena) {
        mAuth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.e("TAG", "Email sent.");
                                            }
                                        }
                                    });

                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("TAG", "createUserWithEmail:failure", task.getException());

                        }
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