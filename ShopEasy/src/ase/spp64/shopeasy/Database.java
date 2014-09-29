package ase.spp64.shopeasy;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public static final String TABLE_LIST = "GROCERY_LIST";
	public static final String COL_GROCERY = "GROCERY_NAME";
	public static final String TABLE_PANTRY = "PANTRY_LIST";
	public static final String COL_PANTRY = "ITEM_NAME";
	List<String> groceryList;
	List<String> pantryList;
	

	public Database(Context context) {
		super(context, "database.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String sql = String
				.format("create table %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)",
						TABLE_LIST, COL_GROCERY);
		String sql2 = String
				.format("create table %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)",
						TABLE_PANTRY, COL_PANTRY);

		db.execSQL(sql);
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void storeGroceryList(String item) {
		SQLiteDatabase db = getReadableDatabase();

		ContentValues values = new ContentValues();
		values.put(COL_GROCERY, item);

		db.insert(TABLE_LIST, null, values);

		db.close();

	}

	public List<String> getGroceryList() {
		groceryList = new ArrayList<String>();
		SQLiteDatabase db = getWritableDatabase();

		String sql = String.format("SELECT %s FROM %s ORDER BY _id",
				COL_GROCERY, TABLE_LIST);
		Cursor cursor = db.rawQuery(sql, null);

		while (cursor.moveToNext()) {

			groceryList.add(cursor.getString(0));
		}

		db.close();
		return groceryList;
	}
	
	public void storePantryList(String item) {
		SQLiteDatabase db = getReadableDatabase();

		ContentValues values = new ContentValues();
		values.put(COL_PANTRY, item);

		db.insert(TABLE_PANTRY, null, values);

		db.close();

	}

	public List<String> getPantryList() {
		pantryList = new ArrayList<String>();
		SQLiteDatabase db = getWritableDatabase();

		String sql = String.format("SELECT %s FROM %s ORDER BY _id",
				COL_PANTRY, TABLE_PANTRY);
		Cursor cursor = db.rawQuery(sql, null);

		while (cursor.moveToNext()) {

			pantryList.add(cursor.getString(0));
		}

		db.close();
		return pantryList;
	}
	
	public void updateGroceryList(List<String> list){
		groceryList = list;
		
	}
	
	public void updatePantryList(List<String> list){
		pantryList = list;
	}
}
