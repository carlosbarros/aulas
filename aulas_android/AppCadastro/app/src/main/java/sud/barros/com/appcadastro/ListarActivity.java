package sud.barros.com.appcadastro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListarActivity extends AppCompatActivity {

    private ListView lvLista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lvLista = (ListView)findViewById(R.id.lvLista);


    }
}
