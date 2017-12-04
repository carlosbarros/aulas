package controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import modelo.Veiculo;

/**
 * Created by carlosbarros on 27/11/2017.
 */

public class ControleVeiculo {

    VeiculoSqlite database;

    public ControleVeiculo(Context ctx)
    {
        database = new VeiculoSqlite(ctx);
    }
    public Veiculo buscar(int codigo)
    {
        Cursor  cursor = database.getConexao().rawQuery("select * from "
                +VeiculoSqlite.TABLE_NAME+ "where codigo="+codigo,null);

        cursor.moveToFirst();
        Veiculo v = new Veiculo();
        v.setCodigo(cursor.getInt(cursor.getColumnIndex(VeiculoSqlite.codigo)));
        v.setPlaca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.placa)));
        v.setModelo(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.modelo)));
        v.setMarca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.marca)));

        return v;
    }
    public long adicionar(Veiculo v)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(VeiculoSqlite.placa,v.getPlaca());
        contentValues.put(VeiculoSqlite.modelo,v.getModelo());
        contentValues.put(VeiculoSqlite.modelo,v.getModelo());
        long codigo = database.getConexao().insert(VeiculoSqlite.TABLE_NAME,null,contentValues);
        return codigo;
    }
    public void editar(Veiculo v)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(VeiculoSqlite.placa,v.getPlaca());
        contentValues.put(VeiculoSqlite.modelo,v.getModelo());
        contentValues.put(VeiculoSqlite.modelo,v.getModelo());

        long codigo = database.getConexao().update(VeiculoSqlite.TABLE_NAME,
                contentValues,"codigo=?",new String[]{Integer.toString(v.getCodigo())});
    }
    public Integer remover(int codigo)
    {
        return database.getConexao().delete(VeiculoSqlite.TABLE_NAME,"codigo=?",
                new String[]{Integer.toString(codigo)});
    }
    public List<Veiculo> listar()
    {
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select codigo, ");
        stringBuilder.append("marca, ");
        stringBuilder.append("modelo, ");
        stringBuilder.append("placa ");
        stringBuilder.append(" from "+VeiculoSqlite.TABLE_NAME+ "");
        stringBuilder.append(" order by marca ");

        Cursor cursor = database.getConexao().rawQuery(stringBuilder.toString(),null);
        cursor.moveToFirst();
        Veiculo v;
        while (!cursor.isAfterLast())
        {
            v = new Veiculo();
            v.setCodigo(cursor.getInt(cursor.getColumnIndex(VeiculoSqlite.codigo)));
            v.setPlaca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.placa)));
            v.setModelo(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.modelo)));
            v.setMarca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.marca)));
            veiculos.add(v);
            cursor.moveToNext();
        }
        return veiculos;

    }

}
