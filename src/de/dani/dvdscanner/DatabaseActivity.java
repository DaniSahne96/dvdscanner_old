package de.dani.dvdscanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.dani.dvdscanner.database.DataSource;
import de.dani.dvdscanner.database.DbRow;
import de.dani.dvdscanner.util.Util;

public class DatabaseActivity extends Activity implements OnClickListener {
	// Database related stuff
	private List<DbRow> historylist = new ArrayList<DbRow>();
	private ArrayAdapter<DbRow> aAdpt;
	private DataSource ds;

	// Android UI Components
	private ListView lv_history;
	private Button btn_cleardb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);

		initMembers();
		

		
		
		switch (getIntent().getIntExtra("calling", 0)) {
		case MainActivity.CALL_ID_MAIN:
			break;
		case MainActivity.CALL_ID_SCAN:
			writeScannedData(getIntent().getStringExtra("ean"), null,null);
			
			break;
		}
		
		lv_history.setAdapter(aAdpt);
		btn_cleardb.setOnClickListener(this);
		rowsToList();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cleardb:
			onBtnClearDbClick();
			break;
		}
	}

	private void onBtnClearDbClick() {
		try {
			ds.open();
			ds.clearDatabase();
			ds.close();
		} catch (Exception e) {
			Util.showDialog(getApplicationContext(), "It's Bobs fault! " +e.toString());
		}
		aAdpt.notifyDataSetChanged();
	}

	private void initMembers() {
		btn_cleardb = (Button) findViewById(R.id.btn_cleardb);
		ds = new DataSource(this);
		historylist = new ArrayList<DbRow>();
		lv_history = (ListView) findViewById(R.id.lv_history);
		aAdpt = new ArrayAdapter<DbRow>(this,
				android.R.layout.simple_expandable_list_item_1,
				android.R.id.text1, historylist);
	}

	private void rowsToList() {
		try {
			ds.open();
			for (int i = 0; i < ds.getRows().size(); i++) {
				historylist.add(ds.getRow(i));
			}
			ds.close();
		} catch (Exception e) {
			Util.showDialog(getApplicationContext(), "It's Bobs fault! " +e.toString());
		}
	}

	public void writeScannedData(String ean, String name, String fsk) {
		try {
			ds.open();
			ds.createRow(ean, "", "");
			ds.close();
		} catch (Exception e) {
			Util.showDialog(getApplicationContext(), "It's Bobs fault! " +e.toString());
		}
	}
	
}
