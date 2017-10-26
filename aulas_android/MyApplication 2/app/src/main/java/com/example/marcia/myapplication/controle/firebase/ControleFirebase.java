package com.example.marcia.myapplication.controle.firebase;

import android.content.Context;

import com.example.marcia.myapplication.modelo.PessoaModel;
import com.example.marcia.myapplication.util.ListaContatosAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcia on 12/12/2016.
 */

public class ControleFirebase {

    private DatabaseReference mDatabase;
    Context context = null;
    private String mUserId;

    public ControleFirebase(Context context){
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public List<PessoaModel> SelecionarTodos(final ListaContatosAdapter mAdapter)
    {
        final List<PessoaModel> pessoasServico =new ArrayList<>();

        mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return pessoasServico;
    }



}
