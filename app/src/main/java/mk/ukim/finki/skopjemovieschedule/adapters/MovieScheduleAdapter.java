package mk.ukim.finki.skopjemovieschedule.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mk.ukim.finki.skopjemovieschedule.R;
import mk.ukim.finki.skopjemovieschedule.models.MovieSchedule;

public class MovieScheduleAdapter extends RecyclerView.Adapter {
    private static String TAG = "MovieScheduleAdapter";
    private List<MovieSchedule> mDataset;

    public class MovieScheduleHolder extends RecyclerView.ViewHolder{

        private TextView mDay;
        private TextView mMovieHall;
        private TextView m3D;
        private TextView mHour;

        MovieScheduleHolder(@NonNull View itemView) {
            super(itemView);
            this.mDay = itemView.findViewById(R.id.textView_day);
            this.mMovieHall = itemView.findViewById(R.id.textView_hall);
            this.m3D = itemView.findViewById(R.id.textView_3d);
            this.mHour = itemView.findViewById(R.id.textView_Hour);
        }

        void setText(MovieSchedule movieSchedule){
            this.mDay.setText(movieSchedule.mDay);
            this.mMovieHall.setText(movieSchedule.mMovieHall);
            this.m3D.setText(movieSchedule.m3D + "");
            this.mHour.setText(movieSchedule.mTime);
        }
    }

    public MovieScheduleAdapter() {
        mDataset = new ArrayList<>();
    }

    public void updateDataset(List<MovieSchedule> newDataset){
        this.mDataset = newDataset;
        this.mDataset.sort(new Comparator<MovieSchedule>() {
            @Override
            public int compare(MovieSchedule o1, MovieSchedule o2) {
                DayOfWeek dayOfWeek1 = DayOfWeek.valueOf(o1.mDay.toUpperCase());
                DayOfWeek dayOfWeek2 = DayOfWeek.valueOf(o2.mDay.toUpperCase());
                return dayOfWeek1.compareTo(dayOfWeek2);
            }
        });
        notifyDataSetChanged();
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
