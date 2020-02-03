package mk.ukim.finki.skopjemovieschedule.omdb;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class omdbMovie {
    public String Title;
    public String Year;
    public String Rated;
    public String Runtime;
    public String Genre;
    public String Director;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Language;
    public String Country;
    public String Awards;
    public String Poster;

    public omdbMovie(String title, String year, String rated, String runtime,
                     String genre, String director, String writer, String actors,
                     String plot, String language, String country, String awards, String poster) {
        Title = title;
        Year = year;
        Rated = rated;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Writer = writer;
        Actors = actors;
        Plot = plot;
        Language = language;
        Country = country;
        Awards = awards;
        Poster = poster;
    }

    @NonNull
    @Override
    public String toString() {
        return Title + " " + Year + " " + Writer + " " + Country + "\n";
    }
}
