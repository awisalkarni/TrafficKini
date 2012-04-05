package legend.dary.traffic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ITISCongestionMap extends Activity{

	private ProgressDialog pb;
	private String htmlContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itis_layout);
		htmlContent = getResources().getString(R.string.html);
		WebView webitis = (WebView) findViewById(R.id.webITIS);
		webitis.loadData(htmlContent, "text/html", "utf-8");
		pb = ProgressDialog.show(ITISCongestionMap.this,"","Loading..Please wait", true); 
		pb.setCancelable(true);
		webitis.getSettings().setJavaScriptEnabled(true);
		webitis.getSettings().setAllowFileAccess(true);
		webitis.getSettings().setPluginsEnabled(true);
		webitis.getSettings().setBuiltInZoomControls(true);
		webitis.setPadding(0, 0, 0, 0);
		webitis.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
//            	pb = ProgressDialog.show(ITISCongestionMap.this,"","Loading..Please wait", true); 
                view.loadUrl(url);
                return false; // then it is not handled by default action
           }
        });
		webitis.setWebChromeClient(new WebChromeClient() {
        	public void onProgressChanged(WebView view, int progress){      		
            	if (progress == 100){
            		pb.dismiss();	    		
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
