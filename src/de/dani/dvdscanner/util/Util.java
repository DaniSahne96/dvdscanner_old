package de.dani.dvdscanner.util;

import android.app.AlertDialog;
import android.content.Context;

public class Util {
	private boolean debuggingEnabled;

	public static void showDialog(Context c, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setMessage(msg);
		builder.show();
	}
}
