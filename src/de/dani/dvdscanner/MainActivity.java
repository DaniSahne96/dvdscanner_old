package de.dani.dvdscanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import de.dani.dvdscanner.util.Util;

public class MainActivity extends Activity implements OnItemClickListener {
	public static final int CALL_ID_SCAN = 0;
	public static final int CALL_ID_MAIN = 1;
	private EditText et_ean;
	private ArrayAdapter<String> a;
	private List<String> activitieslist;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_ean = (EditText) findViewById(R.id.et_ean);

		activitieslist = new ArrayList<String>();
		activitieslist.add("Scannen");
		activitieslist.add("Datenbank");

		a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				activitieslist);
		lv = (ListView) findViewById(R.id.listView1);

		lv.setAdapter(a);
		lv.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
		switch (pos) {
		case 0:
			//Start Activity after scanning
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
			break;
		case 1:
			//Start Activity after clicking in ListView
			Intent i = new Intent(this, DatabaseActivity.class);
			i.putExtra("calling", CALL_ID_MAIN);
			startActivity(i);
			break;
		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanningResult.getContents() != null) {
			Intent i = new Intent(this, DatabaseActivity.class);

			i.putExtra("calling", CALL_ID_SCAN);
			i.putExtra("ean", scanningResult.getContents());
			startActivity(i);
		}
	}
}
