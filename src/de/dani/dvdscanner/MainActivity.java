package de.dani.dvdscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_start_scan_activity, btn_start_history_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_start_scan_activity = (Button) findViewById(R.id.btn_start_scan_activity);
		btn_start_history_activity = (Button) findViewById(R.id.btn_start_history_activity);

		btn_start_scan_activity.setOnClickListener(this);
		btn_start_history_activity.setOnClickListener(this);
	}

	public void onClick(View view) {
		Intent intent;
		if (view.getId() == R.id.btn_start_scan_activity) {
			intent = new Intent(this, ScanActivity.class);
		} else if (view.getId() == R.id.btn_start_history_activity) {
			intent = new Intent(this, HistoryActivity.class);
		} else {
			intent = new Intent(this, MainActivity.class);
		}
		startActivity(intent);
	}
}
