package sud.barros.com.appcadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

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



        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                if(email.equals("carlos@gmail.com") && senha.equals("123"))
                {
                    Intent telaLogin = new Intent(getApplicationContext(),MainActivity.class);
                    telaLogin.putExtra("email",email);
                    startActivity(telaLogin);
                    finish();
                }

            }
        });

    }
}
