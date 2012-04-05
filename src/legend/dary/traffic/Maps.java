package legend.dary.traffic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class Maps extends MapActivity{
	private MapView mapView;
	private MapController mapController;
	private LocationManager locationManager;
	private MyOverlays itemizedoverlay;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.map_layout);
		
		// create a map view
//				LinearLayout linearLayout = (LinearLayout) findViewById(R.id.MapMain);
				mapView = (MapView) findViewById(R.id.mapview);
				mapView.setTraffic(true);
				mapView.setBuiltInZoomControls(true);
				// Either satellite or 2d 
				mapView.setSatellite(true);
				mapController = mapView.getController();
				mapController.setZoom(14); // Zoon 1 is world view
				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
						0, new GeoUpdateHandler());

				Drawable drawable = this.getResources().getDrawable(R.drawable.map_marker);
				itemizedoverlay = new MyOverlays(drawable, this);
				createMarker();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
			createMarker();
			mapController.animateTo(point); // mapController.setCenter(point);

		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	private void createMarker() {
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapView.getOverlays().add(itemizedoverlay);
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
