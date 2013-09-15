package de.dani.dvdscanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "dvdscanner.db";
	private static final int DB_VERSION = 1;

	private static final String TBL_ROW_ID = "_ID";
	private static final String TBL_ROW_EAN = "_EAN";
	private static final String TBL_ROW_NAME = "_NAME";
	private static final String TBL_ROW_FSK = "_FSK";

	private static final String TBL_HISTORY_NAME = "history";
	private static final String TBL_CREATE_HISTORY = "CREATE TABLE "
			+ TBL_HISTORY_NAME + "(" 
			+ TBL_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ TBL_ROW_EAN + " NOT NULL, "
			+ TBL_ROW_NAME + ", " 
			+ TBL_ROW_FSK + ");";

	public static String getTblHistoryName() {
		return TBL_HISTORY_NAME;
	}

	public static String getTblRowId() {
		return TBL_ROW_ID;
	}

	public static String getTblRowEan() {
		return TBL_ROW_EAN;
	}

	public static String getTblCreateHistory() {
		return TBL_CREATE_HISTORY;
	}

	public static String getTblRowName() {
		return TBL_ROW_NAME;
	}

	public static String getTblRowFsk() {
		return TBL_ROW_FSK;
	}

	public MySQLiteHelper(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TBL_CREATE_HISTORY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading Database from version " + oldVersion + " to "
						+ newVersion + " ,which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TBL_HISTORY_NAME);
		onCreate(db);
	}
}
