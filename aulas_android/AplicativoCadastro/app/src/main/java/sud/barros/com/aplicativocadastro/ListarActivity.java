package sud.barros.com.aplicativocadastro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import modelo.Veiculo;

public class ListarActivity extends AppCompatActivity {

    private ListView lvLista;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List veiculos;
    private ArrayAdapter<String> adapter;

   // private String veiculos[]={"gol","corsa","d-20"};


    public void fillData()
    {

        myRef = database.getReference("veiculos");



        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Veiculo t = dataSnapshot.getValue(Veiculo.class);

                veiculos.add(t);
                adapter.notifyDataSetChanged();
                  //  adapterAnuncio.updatePessoaRepositoryServico(anuncios);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // adapter.remove((String) dataSnapshot.child("placa").getValue());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        database = FirebaseDatabase.getInstance();

        myRef= database.getReference();


        lvLista = (ListView)findViewById(R.id.lvLista);
        String filename = "dados";

        byte dados[]=new byte[1024];

        try {
            FileInputStream fos = openFileInput(filename);
            fos.read(dados);
            String d = new String(dados);

            Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();
            fos.close();
        }catch (Exception e)
        {

        }

        veiculos = new ArrayList<Veiculo>();
        veiculos = MenuActivity.controleVeiculo.listar();


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,veiculos);


        lvLista.setAdapter(adapter);

        fillData();

    }
}
