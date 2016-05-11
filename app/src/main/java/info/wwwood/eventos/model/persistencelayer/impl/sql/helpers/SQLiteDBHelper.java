package info.wwwood.eventos.model.persistencelayer.impl.sql.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.StringBuilderPrinter;

import info.wwwood.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 11/05/2016.
 */
public class SQLiteDBHelper extends SQLiteOpenHelper {
    public SQLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //es llença quan el nom de la BD i la versió no existeixen
        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(AppUtils.TABLA_EVENTOS);
        sb.append(" (");
        sb.append(AppUtils.TABLA_EVENTOS_ID);
        sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(AppUtils.TABLA_EVENTOS_EVENTO);
        sb.append(" TEXT");
        sb.append(" )");
        Log.d("CREACIÓN DE BD",sb.toString());
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //entra quan la BD existeix però al paràmetre versió és diferent

    }
}
