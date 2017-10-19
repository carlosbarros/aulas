package sud.barros.com.appcadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etModelo;
    private EditText etMarca;
    private EditText etPlaca;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etModelo = (EditText)findViewById(R.id.etModelo);
        etMarca = (EditText)findViewById(R.id.etMarca);
        etPlaca = (EditText)findViewById(R.id.etPlaca);
        btSalvar = (Button) findViewById(R.id.btSalvar);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.btSalvar:

                break;

        }
    }
}
