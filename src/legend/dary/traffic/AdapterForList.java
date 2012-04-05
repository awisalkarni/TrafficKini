package legend.dary.traffic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterForList extends ArrayAdapter<String>{
	private Context context;
	private String[] textValues;
	private int[] imgValues;
	public AdapterForList(Context context, String[] textValues, int[] imgValues) {
		super(context,R.layout.list_layout, textValues);
		this.context = context;
		this.textValues = textValues;
		this.imgValues = imgValues;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_layout, parent, false);
		TextView sidebarText = (TextView) rowView.findViewById(R.id.sideBarText);
		sidebarText.setText(textValues[position]);
		ImageView sidebarImg = (ImageView) rowView.findViewById(R.id.sideBarImage);
		sidebarImg.setImageResource(imgValues[position]);
		return rowView;
	}

}
