package com.example.marcia.myapplication.view.com.example.marcia.myapplication.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marcia.myapplication.R;
import com.example.marcia.myapplication.controle.list.ContatoControleList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText etUsuario;
    private EditText etSenha;
    private Button btLogar;
    private TextView tvEsqueceuSenha;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public static ContatoControleList controleContatos;
    public static int tipo=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
           // loadLogInView();
        }


        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etSenha = (EditText)findViewById(R.id.etSenha);
        btLogar = (Button)findViewById(R.id.btLogar);
        tvEsqueceuSenha = (TextView)findViewById(R.id.tvEsqueceuSenha);
        btLogar.setOnClickListener(this);
        tvEsqueceuSenha.setOnClickListener(this);
        controleContatos = new ContatoControleList(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btLogar:
                String email = etUsuario.getText().toString();
                String password = etSenha.getText().toString();

                email = email.trim();
                password = password.trim();
                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Please make sure you enter an email address and password! ")
                            .setTitle("Error!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this, TiposActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                    else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle("Error")
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }

                                }
                            });

                }
                break;
            case R.id.tvEsqueceuSenha:
                Intent telaLembrarSenha =new Intent(getApplicationContext(),RegistrarActivity.class);
                startActivity(telaLembrarSenha);
                break;
        }
    }
}
