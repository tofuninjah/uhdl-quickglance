package edu.uhdl.uhdl_quickglance;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WeblogViewActivity extends Activity {

    protected ProgressBar mProgressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weblog_view);
        
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        
        if (isNetworkAvailable()){
			mProgressBar.setVisibility(View.VISIBLE);
			//GetBlogPostsTask getBlogPostsTask = new GetBlogPostsTask();
			//getBlogPostsTask.execute();
		} else {
			Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show();
		}
    
    }
    
    private boolean isNetworkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		
		boolean isAvailable = false;
		
		if(networkInfo != null && networkInfo.isConnected()){
			isAvailable = true;
		}
		
		return isAvailable;
	}


}
