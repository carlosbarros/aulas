package sud.barros.com.appcadastro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etSenha = (EditText)findViewById(R.id.etSenha);
        btLogar = (Button)findViewById(R.id.btLogar);



        btLogar.setOnClickListener(this);


    }
    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onClick(View view) {
     switch (view.getId())
     {

         case R.id.btLogar:
             final String email = etEmail.getText().toString().trim();
             String password = etSenha.getText().toString().trim();

             if(email.equals("carlos@gmail.com") && password.equals("123"))
             {

                 Intent telaHome = new Intent(this,HomeActivity.class);
                 startActivity(telaHome);

             }

             break;

     }
    }
}
