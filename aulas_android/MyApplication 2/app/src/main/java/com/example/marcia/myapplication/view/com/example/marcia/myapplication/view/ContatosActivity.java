package com.example.marcia.myapplication.view.com.example.marcia.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marcia.myapplication.R;
import com.example.marcia.myapplication.controle.firebase.ControleFirebase;
import com.example.marcia.myapplication.controle.list.ContatoControleList;
import com.example.marcia.myapplication.controle.servicos.ContatoControleServico;
import com.example.marcia.myapplication.controle.sqlite.ContatoControleBD;
import com.example.marcia.myapplication.util.ListaContatosAdapter;
import com.example.marcia.myapplication.modelo.PessoaModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by marcia on 26/09/2016.
 */

public class ContatosActivity extends AppCompatActivity {
    ListView listViewPessoas;
    Button buttonVoltar;
    ListaContatosAdapter mAdapter;
    ContatoControleServico pessoaRepositoryServico;
    ControleFirebase pessoaRepositoryFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        listViewPessoas = (ListView) this.findViewById(R.id.listViewPessoas);
        buttonVoltar    = (Button)this.findViewById(R.id.buttonVoltarSqlite);

        this.CarregarPessoasCadastradas(LoginActivity.tipo);

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }
    protected  void CarregarPessoasCadastradas(int tipo){


        switch (tipo){
            case 1:
                ContatoControleBD pessoaRepositoryBD =  new ContatoControleBD(this);
                List<PessoaModel> pessoasBD = pessoaRepositoryBD.SelecionarTodos();
                listViewPessoas.setAdapter(new ListaContatosAdapter(this, pessoasBD,tipo));
                break;
            case 2:
                ContatoControleList pessoaRepositoryList =  LoginActivity.controleContatos;
                List<PessoaModel> pessoasList = pessoaRepositoryList.SelecionarTodos();
                listViewPessoas.setAdapter(new ListaContatosAdapter(this, pessoasList,tipo));
                break;
            case 3:
                pessoaRepositoryServico =  new ContatoControleServico(this);
                List<PessoaModel> pessoas = new ArrayList<>();
                mAdapter = new ListaContatosAdapter(this, pessoas,tipo);
                listViewPessoas.setAdapter(mAdapter);
                pessoaRepositoryServico.SelecionarTodos(mAdapter);
                break;
            case 4:
                pessoaRepositoryFirebase =  new ControleFirebase(this);
                List<PessoaModel> pessoass = new ArrayList<>();
                mAdapter = new ListaContatosAdapter(this, pessoass,tipo);
                listViewPessoas.setAdapter(mAdapter);
              //  pessoaRepositoryFirebase.SelecionarTodos(mAdapter);
                break;
       }
    }

}