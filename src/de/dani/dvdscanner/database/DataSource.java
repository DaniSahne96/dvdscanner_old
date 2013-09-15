package de.dani.dvdscanner.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.getTblRowId(),
			MySQLiteHelper.getTblRowEan(), MySQLiteHelper.getTblRowName(),
			MySQLiteHelper.getTblRowFsk() };

	public DataSource(Context c) {
		dbHelper = new MySQLiteHelper(c);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public DbRow createRow(String _ean, String _name, String _fsk) {
		ContentValues v = new ContentValues();
		v.put("_EAN", _ean);
		v.put("_NAME", _name);
		v.put("_FSK", _fsk);

		long insertId = db.insert(MySQLiteHelper.getTblHistoryName(), null, v);

		Cursor c = db
				.query(MySQLiteHelper.getTblHistoryName(), allColumns,
						MySQLiteHelper.getTblRowId() +" = " + insertId, null, null,
						null, null);
		c.moveToFirst();

		return cursorToDbRow(c);
	}

	public void clearDatabase() {
		db.execSQL("DROP TABLE IF EXISTS " + MySQLiteHelper.getTblHistoryName());
		db.execSQL(MySQLiteHelper.getTblCreateHistory());
	}

	public List<DbRow> getRows() {
		List<DbRow> rowList = new ArrayList<DbRow>();

		Cursor c = db.query(MySQLiteHelper.getTblHistoryName(), allColumns,
				null, null, null, null, null);
		c.moveToFirst();

		if (c.getCount() == 0) {
			return rowList;
		}

		while (c.isAfterLast() == false) {
			DbRow dbr = cursorToDbRow(c);
			rowList.add(dbr);
			c.moveToNext();
		}

		c.close();

		return rowList;
	}

	public DbRow getRow(int pos) {
		Cursor c = db.query(MySQLiteHelper.getTblHistoryName(), allColumns,
				null, null, null, null, null);
		c.moveToPosition(pos);
		
		DbRow dbr = cursorToDbRow(c);
		
		c.close();
		
		return dbr;
	}

	public static DbRow cursorToDbRow(Cursor c){
		DbRow dbr = new DbRow();
		dbr.set_id(c.getLong(0));
		dbr.set_ean(c.getLong(1));
		dbr.set_name(c.getString(2));
		dbr.set_fsk(c.getInt(3));
		
		return dbr;
	}
	//sfsdfsudf
}
