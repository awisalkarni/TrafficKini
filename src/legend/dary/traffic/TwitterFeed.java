package legend.dary.traffic;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import api.Twitter;

public class TwitterFeed extends Activity{
	private Twitter twitter;
	private String data;
	private TextView viewTwitterFeed;
	private ProgressDialog pd;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter);
        
        final String consumerKey = "u2GZRanIjqgLT3g9ySFVDQ",
    			consumerSecret = "6UBy29Id7Ed70e3mKnuaxFgYNVsNox8npH8LBhyD5yo",
    			callbackURL = "http://awislabs.com";
    		
    		twitter = new Twitter(this, consumerKey, consumerSecret, callbackURL);
        Button btShow = (Button) findViewById(R.id.btTwitterFeed);
        viewTwitterFeed = (TextView) findViewById(R.id.txtViewTwitterFeed);
        pd = ProgressDialog.show(TwitterFeed.this,"","Loading..Please wait", true);
//		pd.show();
		getTwitter gt = new getTwitter();
		gt.execute();
        btShow.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				pd = ProgressDialog.show(TwitterFeed.this,"","Loading..Please wait", true);
				getTwitter gt = new getTwitter();
				gt.execute();
//				new Worker("title", "getTwitterData");
				
			}
        	
        });
        }
	public String getTwitterData() throws Exception{
    	return new JSONObject(twitter.get("search","q", "#kltu","rpp",50, "result_type", "recent")).getString("results")+"\n";
    }
	
	public class getTwitter extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			
			try {
				data = getTwitterData();
//				data = readTwitterFeed();
				
			} catch (Exception e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
//			viewTwitterFeed.setText(data);
			viewTwitterFeed.setText("");
			
			try {
				JSONArray jsonarray = new JSONArray(data);
				for (int i = 0;i <jsonarray.length(); i++){
					JSONObject jsonobject = jsonarray.getJSONObject(i);
					viewTwitterFeed.append("-"+jsonobject.getString("text")+" by "+jsonobject.getString("from_user_name")+"\n\n");
					
				}
			}catch (Exception e){
				
			}
			pd.dismiss();
		}
    	
    }
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}
	
	
}
