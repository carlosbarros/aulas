package sud.barros.com.aplicativocadastro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ListarActivity extends AppCompatActivity {

    private ListView lvLista;

    private String veiculos[]={"gol","corsa","d-20"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lvLista = (ListView)findViewById(R.id.lvLista);
        String filename = "dados";

        byte dados[]=new byte[1024];

       // byte dados[]={};

        try {
            FileInputStream fos = openFileInput(filename);
            fos.read(dados);
            String d = new String(dados);

            Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();
            fos.close();
        }catch (Exception e)
        {

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, veiculos);
        lvLista.setAdapter(adapter);

    }
}
