package com.example.marcia.myapplication.controle.servicos;
import cz.msebera.android.httpclient.Header;
/**
 * Created by carlosbarros on 26/04/16.
 */
import android.content.Context;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.example.marcia.myapplication.modelo.PessoaModel;
import com.example.marcia.myapplication.util.ListaContatosAdapter;
import com.example.marcia.myapplication.view.com.example.marcia.myapplication.view.LoginActivity;
import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;
//http://www.programcreek.com/java-api-examples/index.php?api=com.loopj.android.http.JsonHttpResponseHandler


public class ContatoControleServico {

    Context context = null;
    PessoaModel pessoaModel =new PessoaModel();

    public ContatoControleServico(Context context){
        this.context = context;
    }
    public boolean Salvar(PessoaModel c)
    {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("nome",c.getNome());
        params.put("email",c.getEmail());
        params.put("senha","123");
        params.put("endereco",c.getEndereco());
        params.put("sexo",c.getSexo());
        params.put("foto","foto");
        params.put("datanascimento",c.getDataNascimento());
        params.put("estadocivil",c.getEstadoCivil());
        params.put("registroativo",c.getRegistroAtivo());

        client.post("http://192.168.1.7/appAgendaBackend/public/addcontato",params ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context,"Failure",Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }
    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
    public List<PessoaModel> SelecionarTodos(final ListaContatosAdapter mAdapter){
        final List<PessoaModel> pessoasServico =new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get("http://192.168.1.7/appAgendaBackend/public/contatos",new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray contatos = null;
                    if(response != null) {
                        // Get the contatos json array
                        contatos = response.getJSONArray("contatos");

                        for (int i = 0; i < contatos.length(); i++) {
                            JSONObject jsonLineItem = (JSONObject) contatos.getJSONObject(i);
                            PessoaModel pm = PessoaModel.fromJson(jsonLineItem);
                            if (pm != null) {
                                pessoasServico.add(pm);
                            }
                            pm = null;
                        }
                        mAdapter.updatePessoaRepositoryServico(pessoasServico);
                    }
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }
        });
        return pessoasServico;
    }
    public void Excluir(int codigo){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.7/appAgendaBackend/public/contatos/"+codigo;
        client.delete(url,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

               // Toast.makeText(context,"Deletado",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
               // Toast.makeText(context,"Failure DEL",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Atualizar(PessoaModel c)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.7/appAgendaBackend/public/contato/editar?id="
                +c.getCodigo()+"&"
                +"nome="+c.getNome()+"&"
                +"senha=123"+"&"
                +"endereco="+c.getEndereco();

        client.get(url,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context,"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

   public PessoaModel buscar(final int codigo){
       final List<PessoaModel> pessoasServico =new ArrayList<>();
       AsyncHttpClient client = new AsyncHttpClient();
       RequestParams params = new RequestParams();
       client.get("http://192.168.1.7/appAgendaBackend/public/contatos",new JsonHttpResponseHandler() {
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               try {
                   JSONArray contatos = null;
                   if(response != null) {
                       // Get the contatos json array
                       contatos = response.getJSONArray("contatos");
                       for (int i = 0; i < contatos.length(); i++) {
                           JSONObject jsonLineItem = (JSONObject) contatos.getJSONObject(i);
                           PessoaModel pm = PessoaModel.fromJson(jsonLineItem);
                           if (pm != null) {
                               pessoasServico.add(pm);
                           }
                           pm = null;
                       }
                       for (PessoaModel buscaUsuario : pessoasServico) {
                           if (buscaUsuario.getCodigo()==codigo) {
                               pessoaModel = buscaUsuario;
                           }
                       }
                   }
               } catch (JSONException e) {
                   // Invalid JSON format, show appropriate error.
                   e.printStackTrace();
               }
           }
       });
       return pessoaModel;
   }
}