package controle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by carlosbarros on 27/11/2017.
 */

public class VeiculoSqlite extends SQLiteOpenHelper
{

    public static final String DATABASE = "BDNOVO.db";
    public static final String TABLE_NAME = "VEICULO";
    public static final int DATABASE_VERSION = 1;
    //colunas
    public static final String codigo="codigo";
    public static final String marca="marca";
    public static final String modelo="modelo";
    public static final String placa="placa";

    public VeiculoSqlite(Context context) {
        super(context, DATABASE, null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
                + TABLE_NAME +"("+codigo
                +"integer primary key autoincrement,"
                +marca + "text not null,"
                +modelo + "text not null,"
                +placa + "text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME+"");
        onCreate(db);
    }
    public SQLiteDatabase getConexao()
    {
        return this.getWritableDatabase();
    }
}
