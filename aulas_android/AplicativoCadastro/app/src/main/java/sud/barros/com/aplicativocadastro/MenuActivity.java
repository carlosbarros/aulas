package sud.barros.com.aplicativocadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import controle.ControleVeiculo;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    //primeiro passo
    private Button btCadastrar;
    private Button btListar;
    public static ControleVeiculo controleVeiculo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        controleVeiculo = new ControleVeiculo(getApplicationContext());

        //segundo passo
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btListar = (Button)findViewById(R.id.btListar);

        btListar.setOnClickListener(this);

        btCadastrar.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btCadastrar:

                    Intent telaMenu = new Intent(this,CadastrarActivity.class);
                    startActivity(telaMenu);



                break;
            case R.id.btListar:

                Intent telaListar = new Intent(this,ListarActivity.class);
                startActivity(telaListar);



                break;
        }
    }
}
