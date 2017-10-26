package com.example.marcia.myapplication.util;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import com.example.marcia.myapplication.R;
import com.example.marcia.myapplication.controle.list.ContatoControleList;
import com.example.marcia.myapplication.controle.servicos.ContatoControleServico;
import com.example.marcia.myapplication.controle.sqlite.ContatoControleBD;
import com.example.marcia.myapplication.modelo.PessoaModel;
import com.example.marcia.myapplication.view.com.example.marcia.myapplication.view.ContatosActivity;
import com.example.marcia.myapplication.view.com.example.marcia.myapplication.view.EditarActivity;
import com.example.marcia.myapplication.view.com.example.marcia.myapplication.view.LoginActivity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by carlosbarros on 23/09/16.
 */
public class ListaContatosAdapter extends BaseAdapter {

    private int tipo=-1;
    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE PESSOAS
    List<PessoaModel> pessoaModels =  new ArrayList<PessoaModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    ContatoControleBD pessoaRepositoryBD;
    ContatoControleList pessoaRepositoryList;
    ContatoControleServico pessoaRepositoryServico;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ContatosActivity consultarActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE PESSOAS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public ListaContatosAdapter(ContatosActivity consultarActivity, List<PessoaModel> pessoaModels, int tipo ) {

        this.pessoaModels       =  pessoaModels;
        this.consultarActivity  =  consultarActivity;
        this.layoutInflater     = (LayoutInflater) this.consultarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tipo = tipo;
        switch (tipo){
            case 1:
                this.pessoaRepositoryBD   = new ContatoControleBD(consultarActivity);
                break;
            case 2:
                this.pessoaRepositoryList   = new ContatoControleList(consultarActivity);
                break;
            case 3:
                this.pessoaRepositoryServico   = new ContatoControleServico(consultarActivity);
                break;
        }
    }
    public void updatePessoaRepositoryServico(List<PessoaModel> pessoaModels)
    {
        this.pessoaModels = pessoaModels;
        notifyDataSetChanged();
    }
    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount(){

        return pessoaModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT activity_linha_consultar.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.contatos_sqlite_personalizada,null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.

        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);

        //CAMPO QUE VAI MOSTRAR O NOME DA PESSOA
        TextView textViewNome            = (TextView) viewLinhaLista.findViewById(R.id.textViewNome);

        //CAMPO QUE VAI MOSTRAR O ENDEREÇO DA PESSOA
        TextView textViewEndereco        = (TextView) viewLinhaLista.findViewById(R.id.textViewEndereco);

        //CAMPOS QUE VAI MOSTRAR O SEXO DA PESSOA
        TextView textViewSexo            = (TextView) viewLinhaLista.findViewById(R.id.textViewSexo);

        //CAMPO QUE VAI MOSTRAR O ESTADO CIVIL
        TextView textViewEstadoCivil     = (TextView) viewLinhaLista.findViewById(R.id.textViewEstadoCivil);

        //CAMPO QUE VAI MOSTRAR A DATA DE NASCIMENTO
        TextView textViewNascimento      = (TextView) viewLinhaLista.findViewById(R.id.textViewNascimento);

        //CAMPOS QUE VAI MOSTRAR SE O REGISTRO ESTÁ ATIVO OU NÃO
        TextView textViewRegsitroAtivo   = (TextView) viewLinhaLista.findViewById(R.id.textViewRegistroAtivo);

        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        //CRIANDO O BOTÃO PARA EDITAR UM REGISTRO CADASTRADO
        Button   buttonEditar            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditar);

       ImageView imageView2            = (ImageView)   viewLinhaLista.findViewById(R.id.imageView2);


       /* CircularImageView circularImageView = (CircularImageView)viewLinhaLista.findViewById(R.id.imageView2);

    */


        Picasso.with(consultarActivity).load("https://d5f0e2d9-a-62cb3a1a-s-sites.googlegroups.com/site/carlosinfot/home/nova.png?attachauth=ANoY7co9EQKSVPVJcMgUIDZQeTBFkmwOs16yk_dqx51GeZSRq-AjZIyGwr80nMlV5XGlWBciTLbqWkQZ-S3PIpBFxxCRu6XO2LnAQcTrvTDV_eymu78TnuL-T4m7yXXSkcs8J0WPb4cQpSm2LP8Kz-L3VfVXSwUxV_MOUDdOy3fnTb74R0f4EEuUB_Ho_SwdxfvgxZJHtL3yGiAbgYuxXIk5iSN0gwGNQQ%3D%3D&attredirects=0")
                .placeholder(R.drawable.novaok).error(R.drawable.novaerro).into(imageView2);


        //SETANDO O CÓDIGO NO CAMPO DA NOSSA VIEW
        textViewCodigo.setText(String.valueOf(pessoaModels.get(position).getCodigo()));

        //SETANDO O NOME NO CAMPO DA NOSSA VIEW
        textViewNome.setText(pessoaModels.get(position).getNome());

        //SETANDO O ENDEREÇO NO CAMPO DA NOSSA VIEW
        textViewEndereco.setText(pessoaModels.get(position).getEndereco());

        //SETANDO O SEXO NO CAMPO DA NOSSA VIEW
        if(pessoaModels.get(position).getSexo().toUpperCase().equals("M"))
            textViewSexo.setText("Masculino");
        else
            textViewSexo.setText("Feminino");

        //SETANDO O ESTADO CIVIL
        textViewEstadoCivil.setText(this.GetEstadoCivil(pessoaModels.get(position).getEstadoCivil()));

        //SETANDO A DATA DE NASCIMENTO
        textViewNascimento.setText(pessoaModels.get(position).getDataNascimento());

        //SETANDO SE O REGISTRO ESTA ATIVO OU NÃO
        if(pessoaModels.get(position).getRegistroAtivo() == 1)
            textViewRegsitroAtivo.setText("Registro Ativo:Sim");
        else
            textViewRegsitroAtivo.setText("Registro Ativo:Não");

        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //EXCLUINDO UM REGISTRO
                switch (tipo){
                    case 1:
                        pessoaRepositoryBD.Excluir(pessoaModels.get(position).getCodigo());
                        break;
                    case 2:
                        LoginActivity.controleContatos.remover(pessoaModels.get(position));
                        break;
                    case 3:
                        pessoaRepositoryServico.Excluir(pessoaModels.get(position).getCodigo());
                        break;
                }
                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultarActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();

            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRedirecionar = new Intent(consultarActivity, EditarActivity.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id_pessoa",pessoaModels.get(position).getCodigo());

                intentRedirecionar.putExtra("pessoa", pessoaModels.get(position));

                consultarActivity.startActivity(intentRedirecionar);

                consultarActivity.finish();
            }
        });
        return viewLinhaLista;
    }

    //MÉTODO QUE RETORNA A DESCRIÇÃO DO ESTADO CIVIL POR CÓDIGO
    public String GetEstadoCivil(String codigoEstadoCivil){


        if(codigoEstadoCivil.equals("S"))
            return "Solteiro(a)";
        else if(codigoEstadoCivil.equals("C"))
            return "Casado(a)";
        else if(codigoEstadoCivil.equals("V"))
            return "Viuvo(a)";
        else
            return "Divorciado(a)";

    }

    //ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){

        this.pessoaModels.clear();
        switch (tipo){
            case 1:
                this.pessoaModels = pessoaRepositoryBD.SelecionarTodos();
                break;
            case 2:
                this.pessoaModels = LoginActivity.controleContatos.SelecionarTodos();
                break;
            case 3:
                this.pessoaModels = pessoaRepositoryServico.SelecionarTodos(this);
                break;
        }
        this.notifyDataSetChanged();
    }
}