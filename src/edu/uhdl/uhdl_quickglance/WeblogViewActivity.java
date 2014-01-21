package edu.uhdl.uhdl_quickglance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class WeblogViewActivity extends ListActivity {
	private final String KEY_TITLE = "title";
	private final String KEY_DATE = "date";
	public static final String TAG = WeblogViewActivity.class.getSimpleName();
    protected ProgressBar mProgressBar;
    protected JSONObject mWeblogsData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weblog_view);
        
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        
        if (isNetworkAvailable()){
			mProgressBar.setVisibility(View.VISIBLE);
			
			GetBlogPostsTask getBlogPostsTask = new GetBlogPostsTask();
			getBlogPostsTask.execute();
			
		} else {
			Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show();
		}
    
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		JSONArray jsonPosts;
		try {
			jsonPosts = mWeblogsData.getJSONArray("posts");
			
			Log.i(TAG, "Get the JSON Stuff?: " + jsonPosts);
				
			/*
			JSONObject jsonPost = jsonPosts.getJSONObject(position);
			String blogUrl = jsonPost.getString("url");
			
			Intent intent = new Intent(this, BlogWebViewActivity.class);
			intent.setData(Uri.parse(blogUrl));
			startActivity(intent);
			*/
			
			
		} catch (JSONException e) {
			logException(e);
		}
	}
    
    private void logException(Exception e) {
		Log.e(TAG, "Exception caught!", e);
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
    
    private class GetBlogPostsTask extends AsyncTask<Object, Void, JSONObject>{
		@Override
		protected JSONObject doInBackground(Object... params) {
		    int responseCode = -1;
		    JSONObject jsonResponse = null;
		    StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpget = new HttpGet("http://weblogs.lib.uh.edu/?json=1&count=10");

		    try {
		        HttpResponse response = client.execute(httpget);
		        StatusLine statusLine = response.getStatusLine();
		        responseCode = statusLine.getStatusCode();

		        if (responseCode == HttpURLConnection.HTTP_OK) {
		            HttpEntity entity = response.getEntity();
		            InputStream content = entity.getContent();
		            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		            String line;
		            while((line = reader.readLine()) != null){
		                builder.append(line);
		            }

		            jsonResponse = new JSONObject(builder.toString());
		        }
		        else {
		            Log.i(TAG, String.format("Unsuccessful HTTP response code: %d", responseCode));
		        }
		    }
		    catch (JSONException e) {
		        logException(e);
		    }
		    catch (Exception e) {
		        logException(e);
		    }           

		    return jsonResponse;
		}   
			    
	    @Override
		protected void onPostExecute(JSONObject result){
	    	mWeblogsData = result;
			handleBlogResponse();
		}

    }
    
    public void handleBlogResponse() {
		mProgressBar.setVisibility(View.INVISIBLE);
		if(mWeblogsData == null){
			updateDisplayForError();
		} else {
			try {
				JSONArray jsonPosts = mWeblogsData.getJSONArray("posts");
				ArrayList<HashMap<String, String>> blogPosts = new ArrayList<HashMap<String, String>>();
				for(int i = 0; i < jsonPosts.length(); i++){
					JSONObject post = jsonPosts.getJSONObject(i);
					String title = post.getString(KEY_TITLE);
					title = Html.fromHtml(title).toString();
					
					//String author = post.getString(KEY_AUTHOR);
					String date = post.getString(KEY_DATE);
					date = Html.fromHtml(date).toString();
					
					HashMap<String, String> blogPost = new HashMap<String, String>();
					blogPost.put(KEY_TITLE, title);
					blogPost.put(KEY_DATE, date);
					
					blogPosts.add(blogPost);
				}
				
				String[] keys = { KEY_TITLE, KEY_DATE };
				int[] ids = { android.R.id.text1, android.R.id.text2 };
				SimpleAdapter adapter = new SimpleAdapter(this, blogPosts, android.R.layout.simple_list_item_2, keys, ids);
				setListAdapter(adapter);
				
			} catch (JSONException e) {
				logException(e);
			}
		}
	}
    
    private void updateDisplayForError() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.error_title));
		builder.setMessage(getString(R.string.error_message));
		builder.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();
		TextView emptyTextView = (TextView) getListView().getEmptyView();
		emptyTextView.setText(getString(R.string.no_items));
	}

}
