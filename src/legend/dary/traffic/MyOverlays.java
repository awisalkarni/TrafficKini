package legend.dary.traffic;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem> {
	private Context context;
	private static int maxNum = 3;
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	private int index = 0;
	private boolean full = false;
//	private MyOverlays itemizedoverlay;

	public MyOverlays(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	public MyOverlays(Drawable defaultMarker, Context context) {
		this(defaultMarker);
		this.context = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mapOverlays.get(i);
	}

	@Override
	public int size() {
		if (full) {
			return mapOverlays.size();
		} else {
			return index;
		}

	}
	
	@Override
	protected boolean onTap(int index){
		OverlayItem item = mapOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}

	public void addOverlay(OverlayItem overlay) {
		if (index < maxNum) {
			mapOverlays.add(index, overlay);
		} else {
			index = 0;
			full = true;
			mapOverlays.add(index, overlay);
		}
		index++;
		populate();
	}

}