package com.example.marcia.myapplication.modelo;

import java.io.Serializable;

/**
 * Created by marcia on 26/09/2016.
 */

public class EstadoCivilModel implements Serializable{
    private String  codigo;
    private String  descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*RETORNA A DESCRICAO NO COMPONENTE SPINNER */
    @Override
    public String toString() {
        return this.descricao;
    }

    public EstadoCivilModel(){

    }
    public EstadoCivilModel(String codigo, String descricao){

        this.codigo    = codigo;
        this.descricao = descricao;

    }

}