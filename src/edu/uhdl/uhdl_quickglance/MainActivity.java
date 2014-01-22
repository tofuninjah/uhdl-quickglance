package edu.uhdl.uhdl_quickglance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ImageButton weblogsButton;
	ImageButton electronicResourcesButton;
	ImageButton speccolButton;
	ImageButton architectureButton;
	ImageButton musicButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		addListenerOnButtons();
	}

	public void addListenerOnButtons() {
		weblogsButton = (ImageButton) findViewById(R.id.img_button_weblogs);		
		electronicResourcesButton = (ImageButton) findViewById(R.id.img_button_electronic_resources);
		speccolButton = (ImageButton) findViewById(R.id.img_button_speccol);
		architectureButton = (ImageButton) findViewById(R.id.img_button_architecture);
		musicButton = (ImageButton) findViewById(R.id.img_button_music);

		weblogsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Toast.makeText(MainActivity.this, "Weblogs Button clicked!", Toast.LENGTH_SHORT).show();
			    Intent intent = new Intent(MainActivity.this, WeblogViewActivity.class);
				
				startActivity(intent);
			}
		});
		
		electronicResourcesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Toast.makeText(MainActivity.this, "Electronic Resources Button clicked!", Toast.LENGTH_SHORT).show();
			    Intent intent = new Intent(MainActivity.this, ElectronicResourceViewActivity.class);
				startActivity(intent);
			}
		});
		
		speccolButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Toast.makeText(MainActivity.this, "Special Collections Button clicked!", Toast.LENGTH_SHORT).show();
			    //Intent intent = new Intent(MainActivity.this, ElectronicResourceViewActivity.class);
				//startActivity(intent);
			}
		});
		
		architectureButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Toast.makeText(MainActivity.this, "Architecture Button clicked!", Toast.LENGTH_SHORT).show();
			    //Intent intent = new Intent(MainActivity.this, WeblogViewActivity.class);
				//startActivity(intent);
			}
		});
		
		musicButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Toast.makeText(MainActivity.this, "Music Button clicked!", Toast.LENGTH_SHORT).show();
			    //Intent intent = new Intent(MainActivity.this, WeblogViewActivity.class);
				//startActivity(intent);
			}
		});
	}

}
