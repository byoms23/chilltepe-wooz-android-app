package me.wooz.mobile.android.app.policies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.wooz.mobile.android.dto.ensurance.InsuranceType;

/**
 * Created by byron on 9/11/16.
 */

public class InsuranceTypeAdapter extends ArrayAdapter<InsuranceType> {

	public InsuranceTypeAdapter(Context context, List<InsuranceType> insuranceTypes) {
		super(context, android.R.layout.simple_dropdown_item_1line, insuranceTypes);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		// Get the data item for this selectedPosition
		InsuranceType insuranceType = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					android.R.layout.simple_dropdown_item_1line, parent, false);
		}
		// Lookup view for data population
		TextView tvText = (TextView) convertView;
		// Populate the data into the template view using the data object
		tvText.setText(insuranceType.getName());
		// Return the completed view to render on screen
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
		return getView(position, convertView, parent);
	}
}
