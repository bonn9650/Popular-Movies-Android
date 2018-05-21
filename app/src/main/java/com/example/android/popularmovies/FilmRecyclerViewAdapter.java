package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;


public class FilmRecyclerViewAdapter extends RecyclerView
        .Adapter<FilmRecyclerViewAdapter.FilmViewHolder> {

    private ArrayList<Films> mFilms;
    private Context mContext;

    public FilmRecyclerViewAdapter(ArrayList<Films> films, Context context) {
        this.mFilms = films;
        this.mContext = context;
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {

        TextView filmName;
        TextView filmRating;


        public FilmViewHolder(View itemView) {
            super(itemView);

            filmName = itemView.findViewById(R.id.film_title);
            filmRating = itemView.findViewById(R.id.film_rating_star);

        }
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflate list item layout in view holder and return the completed viewholder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_poster_list_item, parent, false);

        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {

        //set the text and images of the film poster by viewholder position
        holder.filmName.setText(mFilms.get(position).getmFilmTitle());
        holder.filmRating.setText(String.valueOf(mFilms.get(position).getmFilmRating()));

    }

    @Override
    public int getItemCount() {
        // returns the number of objects to generate viewholders for
        return mFilms.size();
    }
}
