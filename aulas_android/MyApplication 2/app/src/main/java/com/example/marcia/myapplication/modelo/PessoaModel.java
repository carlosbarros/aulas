package com.example.marcia.myapplication.modelo;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by marcia on 26/09/2016.
 */

public class PessoaModel implements Serializable{
    private Integer codigo;
    private String  nome;
    private String  endereco;
    private String  sexo;
    private String  email;
    private String  dataNascimento;
    private String  estadoCivil;
    private byte    registroAtivo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public byte getRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(byte registroAtivo) {
        this.registroAtivo = registroAtivo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static PessoaModel fromJson(JSONObject jsonObject) {
        PessoaModel p = new PessoaModel();
        try {
            String id = jsonObject.getString("id");
            String nome = jsonObject.getString("nome");
            String email = jsonObject.getString("email");
            String endereco = jsonObject.getString("endereco");
            String sexo = jsonObject.getString("sexo");
            String datanascimento = jsonObject.getString("datanascimento");
            String estadocivil = jsonObject.getString("estadocivil");
            String registroativo = jsonObject.getString("registroativo");
            p.setCodigo(Integer.valueOf(id));
            p.setNome(nome);
            p.setEmail(email);
            p.setEndereco(endereco);
            p.setSexo(sexo);
            p.setDataNascimento(datanascimento);
            p.setEstadoCivil(estadocivil);
            if(registroativo.equals("1"))
                p.setRegistroAtivo((byte)1);
            if(registroativo.equals("0"))
                p.setRegistroAtivo((byte)0);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return p;
    }
}