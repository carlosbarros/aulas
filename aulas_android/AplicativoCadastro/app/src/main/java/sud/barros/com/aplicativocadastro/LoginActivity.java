package sud.barros.com.aplicativocadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //primeiro passo
    private EditText etEmail;
    private EditText etSenha;
    private Button btLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //segundo passo
        etEmail = (EditText)findViewById(R.id.etEmail);
        etSenha = (EditText)findViewById(R.id.etSenha);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btLogin:
                String email= etEmail.getText().toString().trim();
                String senha= etSenha.getText().toString().trim();

                if(email.equals("carlos@gmail.com") && senha.equals("123"))
                {
                    Intent telaMenu = new Intent(this,MenuActivity.class);
                    startActivity(telaMenu);

                }

                break;
        }
    }
}
