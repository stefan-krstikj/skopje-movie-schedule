package mk.ukim.finki.skopjemovieschedule.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.data.MovieSchedule;

public class MovieScheduleAdapter extends RecyclerView.Adapter {
    private static String TAG = "MovieScheduleAdapter";
    private List<MovieSchedule> mDataset;

    public class MovieScheduleHolder extends RecyclerView.ViewHolder{

        private TextView day;
        private TextView movieHall;
        private TextView m3D;
        private TextView hour;

        MovieScheduleHolder(@NonNull View itemView) {
            super(itemView);
            this.day = itemView.findViewById(R.id.textView_day);
            this.movieHall = itemView.findViewById(R.id.textView_hall);
            this.m3D = itemView.findViewById(R.id.textView_3d);
            this.hour = itemView.findViewById(R.id.textView_Hour);
        }

        void setText(MovieSchedule movieSchedule){
            this.day.setText(movieSchedule.mDay);
            this.movieHall.setText(movieSchedule.mMovieHall);
            this.m3D.setText(movieSchedule.m3D + "");
            this.hour.setText(movieSchedule.mTime);
        }
    }

    public MovieScheduleAdapter() {
        mDataset = new ArrayList<>();
    }

    public void updateDataset(List<MovieSchedule> newDataset){
        this.mDataset = newDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_schedule_list_layout,parent, false);
        return new MovieScheduleHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieScheduleHolder) holder).setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
