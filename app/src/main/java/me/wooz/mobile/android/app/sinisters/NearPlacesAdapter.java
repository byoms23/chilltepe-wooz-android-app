package me.wooz.mobile.android.app.sinisters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.places.PlaceLikelihood;

import java.util.List;

import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.dto.NearPlace;

/**
 * Created by byron on 1/11/16.
 */

public class NearPlacesAdapter extends RecyclerView.Adapter<NearPlacesAdapter.NearPlaceViewHolder> {

    private List<NearPlace> nearPlaces ;

    public NearPlacesAdapter(List<NearPlace> nearPlaces) {
        this.nearPlaces = nearPlaces;
    }

    @Override
    public NearPlacesAdapter.NearPlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_place, viewGroup, false);

        return new NearPlacesAdapter.NearPlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NearPlacesAdapter.NearPlaceViewHolder holder, int position) {
        holder.tvPlace.setText(nearPlaces.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return nearPlaces.size();
    }

    protected View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    /*
     *
     */
    public static class NearPlaceViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPlace;

        public NearPlaceViewHolder(View itemView) {
            super(itemView);
            this.tvPlace = (TextView) itemView.findViewById(R.id.tv_place);
        }
    }

}
