package sud.barros.com.aplicativocadastro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;

import modelo.Veiculo;

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btSalvar;
    private EditText etPlaca;
    private EditText etMarca;
    private EditText etModelo;
    private Spinner spCategoria;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private String categorias[]={"esportivo","sw4", "utilitário","basico","completo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        btSalvar = (Button)findViewById(R.id.btSalvar);
        etPlaca = (EditText)findViewById(R.id.etPlaca);
        etMarca = (EditText)findViewById(R.id.etMarca);
        etModelo = (EditText)findViewById(R.id.etModelo);
        spCategoria = (Spinner)findViewById(R.id.spCategoria);

        btSalvar.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCategoria.setAdapter(adapter);


        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btSalvar:

                String placa = etPlaca.getText().toString().trim();
                String modelo = etModelo.getText().toString().trim();
                String marca = etMarca.getText().toString().trim();

                Veiculo v = new Veiculo(marca,modelo,placa);

                MenuActivity.controleVeiculo.adicionar(v);

                myRef = database.getReference("veiculos");

                myRef.push().setValue(v, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                        String uniqueKey = databaseReference.getKey();

                        Toast.makeText(CadastrarActivity.this, "Cadastrado.",
                                Toast.LENGTH_SHORT).show();


                    }
                });


                String dados = placa + " "+ modelo+ " "+ marca;
                String filename = "dados";

                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    fos.write(dados.getBytes());
                    fos.close();
                }catch (Exception e)
                {

                }

                break;
        }
    }
}
