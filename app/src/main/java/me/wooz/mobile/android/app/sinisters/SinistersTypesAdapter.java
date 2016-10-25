package me.wooz.mobile.android.app.sinisters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.wooz.mobile.android.app.R;

/**
 * Created by byron on 23/10/16.
 */

public class SinistersTypesAdapter extends RecyclerView.Adapter<SinistersTypesAdapter.SinisterTypeViewHolder> {

    @Override
    public SinisterTypeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_sinister_type, viewGroup, false);

        return new SinisterTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SinisterTypeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    protected View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    /*
     *
     */
    public static class SinisterTypeViewHolder extends RecyclerView.ViewHolder {

        public SinisterTypeViewHolder(View itemView) {
            super(itemView);
        }
    }
}