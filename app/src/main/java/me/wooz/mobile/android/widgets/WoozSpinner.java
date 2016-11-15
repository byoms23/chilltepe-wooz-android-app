package me.wooz.mobile.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by byron on 9/11/16.
 */

public class WoozSpinner extends MaterialBetterSpinner {

	protected int selectedPosition = -1;

	public WoozSpinner(Context context) {
		super(context);
	}

	public WoozSpinner(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
	}

	public WoozSpinner(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		super.onItemClick(adapterView, view, i, l);
		this.selectedPosition = i;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}
}
