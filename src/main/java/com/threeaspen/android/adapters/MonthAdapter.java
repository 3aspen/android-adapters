/**
 * 
 */
package com.threeaspen.android.adapters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MonthAdapter extends BaseAdapter implements SearchableAdapter {
	private static final DateFormat df = new SimpleDateFormat("MMMM");
	private Context context;
	
	public MonthAdapter(Context context) {
		this.context = context;
	}
	public int getCount() { return 12; }
	public long getItemId(int position) { return position+1; }
	public Object getItem(int position) { return position+1; }
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv;
        if (convertView == null) {
            tv = (TextView) LayoutInflater.from(context).inflate(
                    android.R.layout.simple_spinner_item, parent, false);
        } else {
            tv = (TextView) convertView;
        }
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), position, 1);
		tv.setText(df.format(c.getTime()));
        return tv;
	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView tv;
        if (convertView == null) {
            tv = (TextView) LayoutInflater.from(context).inflate(
                    android.R.layout.simple_spinner_dropdown_item, parent, false);
        } else {
            tv = (TextView) convertView;
        }
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), position, 1);
		tv.setText(df.format(c.getTime()));
        return tv;
	}
	
	public int getPosition(Object value) {
		if (value instanceof Number) {
			Number n = (Number)value;
			return n.intValue()-1;
		}
		if (value instanceof String) {
			try {
				return Integer.parseInt((String)value)-1;
			} catch (NumberFormatException e) {
				return -1;
			}
		}
		return -1;
	}
	
}