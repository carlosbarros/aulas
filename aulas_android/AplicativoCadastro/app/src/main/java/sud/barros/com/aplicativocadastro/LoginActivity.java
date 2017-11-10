package sud.barros.com.aplicativocadastro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //primeiro passo
    private EditText etEmail;
    private EditText etSenha;
    private Button btLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //segundo passo
        etEmail = (EditText)findViewById(R.id.etEmail);
        etSenha = (EditText)findViewById(R.id.etSenha);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btLogin:


                String email= etEmail.getText().toString().trim();
                String senha= etSenha.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(LoginActivity.this, "Logado.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent telaMenu = new Intent(getApplicationContext(),MenuActivity.class);
                                    startActivity(telaMenu);

                                   // updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                  //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                 //   updateUI(null);
                                }

                                // ...
                            }
                        });

               /* if(email.equals("carlos@gmail.com") && senha.equals("123"))
                {


                    Intent telaMenu = new Intent(this,MenuActivity.class);
                    startActivity(telaMenu);

                }*/

                break;
        }
    }
}
