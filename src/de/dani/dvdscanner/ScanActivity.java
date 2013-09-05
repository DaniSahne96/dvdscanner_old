package de.dani.dvdscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends Activity implements OnClickListener {
	private Button btn_scan;
	private TextView tv_format, tv_content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		
		btn_scan = (Button)findViewById(R.id.btn_scan);
		tv_format = (TextView)findViewById(R.id.tv_format);
		tv_content = (TextView)findViewById(R.id.tv_content);

		btn_scan.setOnClickListener(this);
	}
	
	public void onClick(View view){
		if(view.getId() == R.id.btn_scan){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if(scanningResult != null){
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			
			tv_format.setText("Format: " +scanFormat);
			tv_content.setText("Content: " +scanContent);
		}else{
			Toast toast = Toast.makeText(getApplicationContext(), R.string.txt_no_data, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
