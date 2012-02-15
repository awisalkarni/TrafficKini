package legend.dary.traffic;

import org.json.*;
import android.content.*;
import api.*;

/** @see http://dev.twitter.com/doc */
class TwitterView extends AbstractView {
	
	private Twitter twitter;

	public TwitterView(Context context) {
		super(context, "POC twitter fetch");
		
		final String consumerKey = "...",
			consumerSecret = "...",
			callbackURL = "http://awislabs.com";
		
		twitter = new Twitter(context, consumerKey, consumerSecret, callbackURL);
		
		addButton("View #kltu", "search");
//		addButton("Send a tweet", "updateStatus");
//		addButton("Upload profile image", "updateProfileImage");
		
		setButtonsVisible(twitter.isAuthorized());
	}
	
	protected void authorization() {
		twitter.showAuthorization().setOnDismissListener(this);
	}

	public void onDismiss(DialogInterface dialog) { // DialogInterface.OnDismissListener
		setButtonsVisible(twitter.isAuthorized());
	}
	
	/** @see http://dev.twitter.com/doc/get/account/totals */
	public String search() throws Exception {
//		return new JSONObject(twitter.get("account/totals")).getInt("followers");
		String s = "";
//		for (int i=0;i<10;i++){
			s = new JSONObject(twitter.get("search","q", "#kltu", "result_type", "recent")).getString("results")+"\n";
//		}
		return s;
	}

	/** @see http://dev.twitter.com/doc/post/statuses/update */
	public String updateStatus() throws Exception {
		return twitter.post("statuses/update",
			"status", "Meeting.", "lat", 38.897669, "long", -77.03655);
	}

	/** @see http://dev.twitter.com/doc/post/account/update_profile_image */
	public String updateProfileImage() throws Exception {
		return twitter.post("account/update_profile_image",
			"image", getResources().getAssets().open("image.png"));
	}
}
