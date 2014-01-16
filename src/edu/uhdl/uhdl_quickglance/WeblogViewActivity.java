package edu.uhdl.uhdl_quickglance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class WeblogViewActivity extends Activity {

    protected ProgressBar mProgressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weblog_view);
        
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        
    
    }


}
