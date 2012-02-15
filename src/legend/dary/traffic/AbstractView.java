package legend.dary.traffic;

import java.io.*;
import java.lang.reflect.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

abstract class AbstractView extends LinearLayout implements DialogInterface.OnDismissListener {
	TrafficKiniActivity tka = new TrafficKiniActivity();
	protected AbstractView(Context context, String title) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
//
//		TextView titleView = new TextView(getContext());
//		titleView.setText(title); titleView.setTypeface(Typeface.SERIF); titleView.setTextSize(18);
//		titleView.setBackgroundColor(0xff404040); titleView.setTextColor(0xff99ccff);
//		titleView.setPadding(gap, gap, gap, gap);
//		addView(titleView);

		TextView authButton = new TextView(getContext());
		authButton.setText("\u25b6 " + "Authorization"); authButton.setTypeface(Typeface.DEFAULT_BOLD); 
		authButton.setTextColor(0xffffcc99);
		authButton.setBackgroundResource(android.R.drawable.list_selector_background);
		authButton.setFocusable(true); authButton.setPadding(gap, gap, gap, gap);
		authButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) { authorization(); }
		});
		addView(authButton);
	}
	
	protected abstract void authorization();
	
	protected void addButton(final String title, final String method) {
		TextView button = new TextView(getContext());
		button.setText("\u25b6 " + title); button.setTypeface(Typeface.DEFAULT_BOLD);
		button.setTextColor(0xffcccccc);
		button.setBackgroundResource(android.R.drawable.list_selector_background);
		button.setFocusable(true); button.setPadding(gap, gap, gap, gap);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) { new Worker(title, method); }
		});
		addView(button);
	}
	
	protected void setButtonsVisible(boolean visible) {
		for (int i = 2; i < getChildCount(); i++) getChildAt(i).setVisibility(visible ? View.VISIBLE : View.GONE);
	}
	
	private class Worker implements Runnable {
		
		private ProgressBar progressBar;
		private TextView statusView;
		private String method;
		private Handler handler;
		private boolean fininshed;
		private Object result;
		private Throwable exc;
		
		public Worker(String title, String method) {
			Dialog dialog = new Dialog(getContext());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			FrameLayout content = new FrameLayout(getContext());
			content.setPadding(gap, gap, gap, gap);
			
			progressBar = new ProgressBar(getContext());
			content.addView(progressBar, new FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

			ScrollView scrollView = new ScrollView(getContext());
			statusView = new TextView(getContext());
//			statusView.setText(title); 
			statusView.setTypeface(Typeface.DEFAULT_BOLD); 
			scrollView.addView(statusView);
			content.addView(scrollView);
//			content.addView(scrollView, new ViewGroup.LayoutParams(scale(280), scale(128)));
			dialog.setContentView(content);

			dialog.setCanceledOnTouchOutside(true);
			dialog.show();
			
			this.method = method;
			handler = new Handler();
			new Thread(this).start();
		}

		public void run() { // Runnable
			if (!fininshed) {
				try {
					result = AbstractView.this.getClass().getMethod(method).invoke(AbstractView.this);
//					tka.setResult(result.toString());
				} catch (Throwable exc) { this.exc = exc; }
				fininshed = true;
				handler.post(this);
			}
			else {
				progressBar.setVisibility(View.INVISIBLE);
				if (exc != null) {
					if (exc instanceof InvocationTargetException) exc = exc.getCause();
					StringWriter sw = new StringWriter();
					exc.printStackTrace(new PrintWriter(sw));
					statusView.setText(sw.toString()); statusView.setTextColor(0xffffcccc);
					exc.printStackTrace();
				}
				else {
					statusView.setText((result != null) ? result.toString() : "");
//					tka.setResult(result.toString());
				}
			}
		}
	}
	
	private final int gap = scale(8);
	private final int scale(int value) { return (int) (value * getResources().getDisplayMetrics().density); }
}
