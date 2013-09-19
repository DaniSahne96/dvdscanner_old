package de.dani.dvdscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import de.dani.dvdscanner.database.DataSource;

public class ScanActivity extends Activity implements OnClickListener {
	private Button btn_scan, btn_save;
	private EditText et_ean, et_name, et_fsk;
	private TextView tv_debug;
	private DataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);

		initMembers();

		btn_scan.setOnClickListener(this);
		btn_save.setOnClickListener(this);
	} 

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_scan:
			onBtnScanClick();
			break;
		case R.id.btn_save:
			onBtnSaveClick();
			break;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanningResult != null) {
			et_ean.setText(scanningResult.getContents());
		}
	}

	public void onBtnScanClick() {
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}

	public void onBtnSaveClick() {
		String ean = et_ean.getText().toString();
		String name = et_name.getText().toString();
		String fsk = et_fsk.getText().toString();

		try {
			ds.open();
			ds.createRow(ean, name, fsk);
			ds.close();
			Toast.makeText(this, "Sucess!", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG)
					.show();
			tv_debug.setText(e.toString());
		}

		Intent intent = new Intent(this, DatabaseActivity.class);
		startActivity(intent);

	}
	
	public void initMembers() {
		btn_scan = (Button) findViewById(R.id.btn_scan);
		btn_save = (Button) findViewById(R.id.btn_save);
		et_ean = (EditText) findViewById(R.id.et_ean);
		et_name = (EditText) findViewById(R.id.et_name);
		et_fsk = (EditText) findViewById(R.id.et_fsk);
		tv_debug = (TextView) findViewById(R.id.tv_debug);
		ds = new DataSource(this);
	}
}





