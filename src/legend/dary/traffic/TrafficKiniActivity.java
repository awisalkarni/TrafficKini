package legend.dary.traffic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TrafficKiniActivity extends Activity {
	private ListView listView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] textvalues = {"Map", "TwitterFeed", "ITIS Congestion Map(Beta)"};
        int[] imgvalues = {1,2,3};
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new AdapterForList(this, textvalues, imgvalues));
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
				try{
            		if (pos == 0){
            			Intent mapsIntent = new Intent(TrafficKiniActivity.this, Maps.class);
            			startActivity(mapsIntent);
            			overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            		}else if (pos == 1){
            			Intent twitterIntent = new Intent(TrafficKiniActivity.this, TwitterFeed.class);
            			startActivity(twitterIntent);
            			overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            		}else if (pos == 2){
            			Intent ITISIntent = new Intent(TrafficKiniActivity.this, ITISCongestionMap.class);
            			startActivity(ITISIntent);
            			overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            		}
            	}catch (ArrayIndexOutOfBoundsException e){
            		Log.e("array2","ArrayIndexOutOfBoundsException line 149");
            	}catch (IndexOutOfBoundsException e){
            		Log.e("array2","IndexOutOfBoundsException line 151");
            	}
				    
            	}
        });
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