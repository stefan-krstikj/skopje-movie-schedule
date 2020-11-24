package com.stefankrstikj.skopjemovieschedule.adapters;

import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.stefankrstikj.skopjemovieschedule.R;
import com.stefankrstikj.skopjemovieschedule.models.MapLocation;
import com.stefankrstikj.skopjemovieschedule.ui.maps.MapsFragment;
import com.stefankrstikj.skopjemovieschedule.ui.maps.OnMapLocationClickListener;

public class MapLocationAdapter extends RecyclerView.Adapter {
    private static String TAG = "MapLocationAdapter";
    private List<MapLocation> mDataset;
    private OnMapLocationClickListener mListener;

    public class MapLocationHolder extends RecyclerView.ViewHolder{
        private TextView mTheaterName;
        private TextView mTheaterRating;
        private TextView mTheaterAddress;
        private TextView mTheaterDistance;
        private TextView mOpenNowTrue;
        private TextView mOpenNowFalse;


        MapLocationHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onMapLocationClick(mDataset.get(getAdapterPosition()));
                }
            });
            initViews(itemView);
        }

        void initViews(View itemView){
            this.mTheaterName = itemView.findViewById(R.id.textView_theaterName);
            this.mTheaterAddress = itemView.findViewById(R.id.textView_theaterAddress);
            this.mOpenNowTrue = itemView.findViewById(R.id.textView_theaterOpenNowTrue);
            this.mOpenNowFalse = itemView.findViewById(R.id.textView_theaterOpenNowFalse);
            this.mTheaterDistance = itemView.findViewById(R.id.textView_theaterDistance);
        }



        void setText(MapLocation mapLocation){
            this.mTheaterName.setText(mapLocation.getName());
            this.mTheaterAddress.setText(mapLocation.getVicinity());
            this.mTheaterDistance.setText(calculateDistance(mapLocation));
            if(mapLocation.getOpenNow() == 0) {
                this.mOpenNowTrue.setVisibility(View.INVISIBLE);
                this.mOpenNowFalse.setVisibility(View.VISIBLE);
            }
//            this.mTheaterRating.setText(mapLocation.getRating());
        }

        String calculateDistance(MapLocation mapLocation){
            Location lastLocation = MapsFragment.mLastLocation;
            double myLongitude = Math.toRadians(lastLocation.getLongitude());
            double myLatitude = Math.toRadians(lastLocation.getLatitude());

            double mapLocationLongitude = Math.toRadians(Double.parseDouble(mapLocation.getLongitude()));
            double mapLocationLatitude = Math.toRadians(Double.parseDouble(mapLocation.getLatitude()));

            double dlon = mapLocationLongitude - myLongitude;
            double dlat = mapLocationLatitude - myLatitude;

            double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(myLatitude) * Math.cos(mapLocationLatitude)* Math.pow(Math.sin(dlon / 2),2);
            double c = 2 * Math.asin(Math.sqrt(a));
            double r = 6371;
            double distance = c * r;

            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(distance) + "km";
        }
    }

    public MapLocationAdapter(OnMapLocationClickListener listener){
        this.mDataset = new ArrayList<>();
        this.mListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout_theater, parent, false);
        return new MapLocationHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MapLocationHolder) holder).setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateDataset(List<MapLocation> data){
        this.mDataset = data;
        Log.v(TAG + "updated dataset: ", data.toString());
        notifyDataSetChanged();
    }
}
