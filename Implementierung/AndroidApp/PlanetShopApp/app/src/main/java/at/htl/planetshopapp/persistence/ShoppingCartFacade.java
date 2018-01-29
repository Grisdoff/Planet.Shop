package at.htl.planetshopapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.service.DataService;

/**
 * Created by Daniel Mazanek on 29.01.2018.
 */

public class ShoppingCartFacade extends Db {
    private static ShoppingCartFacade shoppingCartFacade;

    public static ShoppingCartFacade getShoppingCartFacade() {
        return shoppingCartFacade;
    }

    public static void setShoppingCartFacade(ShoppingCartFacade value) {
        shoppingCartFacade = value;
    }

    protected static final String TABLE_NAME = "SHOPPINGCART";
    private DataService dataService = DataService.getInstance();
    public ShoppingCartFacade(Context context) {
        super(context);
    }


    public void add(PlanetCard planetCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", planetCard.getId());
        values.put("ITEMCOUNT", planetCard.getItemCount());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void get(Consumer<List<PlanetCard>> cardLoader) {
        ArrayList< PlanetCard > firstTableDataList = new ArrayList();
        String refQuery = "Select * From " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(refQuery, null);
        try {
            List<Long> ids = new ArrayList<>();
            List<Integer> itemCounts = new ArrayList<>();
            while (cursor.moveToNext()) {
                ids.add(cursor.getLong(0));
                itemCounts.add(cursor.getInt(1));
            }
            DataService.getInstance().loadPlanetCards(ids, it -> {
                int index = 0;
                for (PlanetCard i: it) {
                    i.setItemCount(itemCounts.get(index));
                    index++;
                }
                cardLoader.accept(it);
            });
        } finally {

            db.close();
        }
    }

    public void get(Predicate p) {
        return;
    }

    public void update(PlanetCard planetCard) {
            update(planetCard.getId(), planetCard.getItemCount());
    }

    public void update(Long id, Integer itemCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ITEMCOUNT", itemCount);
        String[] whereArgs = new String[1];
        whereArgs[0] = id.toString();
        db.update(TABLE_NAME, values, "ID=?",    whereArgs);
        db.close();
    }

    public void delete(PlanetCard planetCard) {
        delete(planetCard.getId());
    }

    public void delete(Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = new String[1];
        whereArgs[0] = id.toString();
        db.delete(TABLE_NAME, "ID=?", whereArgs);
        db.close();
    }

    public int getItemCountOf(Long itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT ITEMCOUNT FROM %s WHERE ID = %s", TABLE_NAME, itemId.toString()), null);
        int returnValue = -1;
        if (cursor.moveToNext()) {

            returnValue = cursor.getInt(0);
        }
        db.close();
        return returnValue;
    }

    public void incrementItemCountOf(Long itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int itemCount;
        if ((itemCount = getItemCountOf(itemId)) > 0) {
            itemCount++;
            update(itemId, itemCount);
        }
        else {
            PlanetCard tmp = new PlanetCard(itemId);
            tmp.setItemCount(1);
            add(tmp);
        }
    }
}
