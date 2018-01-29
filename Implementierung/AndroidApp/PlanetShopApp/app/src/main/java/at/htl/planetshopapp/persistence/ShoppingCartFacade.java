package at.htl.planetshopapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.service.DataService;

/**
 * Created by Daniel Mazanek on 29.01.2018.
 */

public class ShoppingCartFacade extends Db {
    protected static final String TABLE_NAME = "SHOPPINGCART";
    private DataService dataService = DataService.getInstance();
    public ShoppingCartFacade(Context context) {
        super(context);
    }

    public void add(PlanetCard planetCard, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", planetCard.getId());
        values.put("ITEMCOUNT", count);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void get(Consumer<PlanetCard> cardLoader) {
        ArrayList< PlanetCard > firstTableDataList = new ArrayList();
        String refQuery = "Select * From " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(refQuery, null);
        try {
            if (cursor.moveToNext()) {
                do {
                    final Integer itemCount = cursor.getInt(1);
                    dataService.loadPlanetCard(cursor.getLong(0), it -> {
                        it.setItemCount(itemCount);
                        cardLoader.accept(it);
                    });
                } while (cursor.moveToNext());
            }
        } finally {

            db.close();
        }
    }

    public void get(Predicate p) {
        return;
    }

    public void update(PlanetCard planetCard, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", planetCard.getId());
        values.put("ITEMCOUNT", count);
        db.update(TABLE_NAME, values, "ID = " + planetCard.getId(),    null);
        db.close();
    }

    public void delete(PlanetCard planetCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, planetCard.getId() + " = ID", null);
        db.close();
    }
}
