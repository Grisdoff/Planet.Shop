package at.htl.planetshopapp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daniel Mazanek on 29.01.2018.
 */

public class Db extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlanetShopDb";
    protected static final String TABLE_NAME = "SHOPPINGCART";

    protected static final String CREATE_TABLE = "create table if not exists "
            + TABLE_NAME
            + " (ID integer primary key autoincrement, ITEMCOUNT integer NOT NULL);";

    public Db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Nur was zu tun wenn neue Db Version
    }
}
