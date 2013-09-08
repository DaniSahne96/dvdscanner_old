package de.dani.dvdscanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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

public class HistoryActivity extends Activity implements OnClickListener {
	private List<DbRow> historylist = new ArrayList<DbRow>();
	private TextView tv_debug;
	private ListView lv_history;
	private Button btn_cleardb;
	private ArrayAdapter<DbRow> aAdpt;
	private DataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		initMembers();
		
		try {
			ds.open();
			for (int i = 0; i < ds.getRows().size(); i++) {
				historylist.add(ds.getRow(i));
			}
			ds.close();
		} catch (Exception e) {
			Toast.makeText(this, "Somesthing went wrong", Toast.LENGTH_LONG)
					.show();
			tv_debug.setText(e.toString());
		}

		lv_history.setAdapter(aAdpt);
		btn_cleardb.setOnClickListener(this);
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
			Toast.makeText(this, "Sucess!", Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(this, "Something went wrong!",
					Toast.LENGTH_SHORT).show();
			tv_debug.setText(ex.toString());
		}
	}

	private void initMembers() {
		tv_debug = (TextView) findViewById(R.id.tv_debug);
		btn_cleardb = (Button) findViewById(R.id.btn_cleardb);
		ds = new DataSource(this);
		historylist = new ArrayList<DbRow>();
		lv_history = (ListView) findViewById(R.id.lv_history);
		aAdpt = new ArrayAdapter<DbRow>(this,
				android.R.layout.simple_expandable_list_item_1,
				android.R.id.text1, historylist);
	}

}
