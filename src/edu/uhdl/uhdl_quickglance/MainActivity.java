package edu.uhdl.uhdl_quickglance;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	ImageButton weblogsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addListenerOnButton() {
		weblogsButton = (ImageButton) findViewById(R.id.img_button_weblogs);
		weblogsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Toast.makeText(MainActivity.this, "Weblogs Button is clicked!", Toast.LENGTH_SHORT).show();
			    Intent intent = new Intent(MainActivity.this, WeblogViewActivity.class);
				
				startActivity(intent);
			}
		});
	}

}
