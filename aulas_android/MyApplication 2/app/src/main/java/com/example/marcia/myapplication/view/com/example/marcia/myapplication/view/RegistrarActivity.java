package com.example.marcia.myapplication.view.com.example.marcia.myapplication.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marcia.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarActivity extends AppCompatActivity {

    private EditText etMail;
    private EditText etSenha;
    private Button btEnviar;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        etMail = (EditText)findViewById(R.id.etMail);
        etSenha = (EditText)findViewById(R.id.etSenha);

        btEnviar = (Button)findViewById(R.id.btEnviar);

        Intent intentOrigem = getIntent();

        String email = intentOrigem.getStringExtra("email");
        etMail.setText(email);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = etMail.getText().toString();
                String email = etSenha.getText().toString();

                password = password.trim();
                email = email.trim();


                if (password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarActivity.this);
                    builder.setMessage("Please make sure you enter an email address and password!")
                            .setTitle("Error!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegistrarActivity.this, TiposActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle("Please make sure you enter an email address and password!")
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
            }
        });


    }
}
