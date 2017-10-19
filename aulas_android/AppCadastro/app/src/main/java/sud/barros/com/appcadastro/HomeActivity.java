package sud.barros.com.appcadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btCadastar;
    private Button btListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btCadastar = (Button)findViewById(R.id.btCadastar);
        btListar = (Button)findViewById(R.id.btListar);

        btCadastar.setOnClickListener(this);
        btListar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.btCadastar:
                Intent telaCadastro = new Intent(this,CadastroActivity.class);
                startActivity(telaCadastro);
                break;
            case R.id.btListar:
                Intent telaHome = new Intent(this,ListarActivity.class);
                startActivity(telaHome);
                break;

        }
    }
}
