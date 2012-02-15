package legend.dary.traffic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TrafficKiniActivity extends Activity {
//	String result;
//	TextView txt;
	Context context = this;
//	AbstractView a = new AbstractView(context,"");
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
		LinearLayout content = new LinearLayout(this);
		content.setOrientation(LinearLayout.VERTICAL);
//		txt = new TextView(this);
		content.addView(new TwitterView(this));
//		content.addView(txt);
		scrollView.addView(content);
		setContentView(scrollView);
//        Dialog twitterDialog = new Dialog(this);
//		TwitterView tw = new TwitterView(this);
//		twitterDialog.addContentView(tw, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//				ViewGroup.LayoutParams.FILL_PARENT));
////		twitterDialog.setTitle("Share on Twitter");
//		twitterDialog.setTitle("POC");
//		twitterDialog.setCancelable(true);
//		twitterDialog.show();
    }
    
//    public void setResult(String result){
////    	this.result = result;
//    	txt.setText(result);
//    }
}