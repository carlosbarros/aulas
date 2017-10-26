package com.example.marcia.myapplication.controle.list;



import android.content.Context;

import com.example.marcia.myapplication.modelo.PessoaModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aluno on 17/09/2016.
 */
public class ContatoControleList {

    private List<PessoaModel> pessoas;
    private int posicaoEncontrada;
    private Context ctx;

    public ContatoControleList(Context context){
        this.ctx = ctx;
        pessoas = new ArrayList<>();
    }

    public PessoaModel buscar(Integer id){

        for (PessoaModel buscaUsuario : pessoas) {
            if (buscaUsuario.getCodigo()==id) {
                return buscaUsuario;
            }
        }
        return null;
    }
    public boolean adicionar(PessoaModel c){

        if(buscar(c.getCodigo())==null) {
            pessoas.add(c);
            return true;
        }else{
        }
        return false;
    }
    public boolean remover(PessoaModel c){
        if(buscar(c.getCodigo())!=null) {
            pessoas.remove(posicaoEncontrada);
            return true;
        }
        return false;
    }
    public boolean editar(PessoaModel c){
        if(buscar(c.getCodigo())!=null) {

            pessoas.set(posicaoEncontrada,c);
            return true;
        }
        return false;
    }
    public List<PessoaModel> SelecionarTodos()
    {
        return pessoas;
    }
}
