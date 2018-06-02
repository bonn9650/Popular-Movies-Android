package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.popularmovies.TheMovieDBAPI.Films;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FilmRecyclerViewAdapter extends RecyclerView
        .Adapter<FilmRecyclerViewAdapter.FilmViewHolder> {

    private List<Films> mFilms;
    private Context mContext;
    private final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342";

    public FilmRecyclerViewAdapter(List<Films> films, Context context) {
        this.mFilms = films;
        this.mContext = context;
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {

        TextView filmName;
        TextView filmRating;
        ImageView filmImage;


        public FilmViewHolder(View itemView) {
            super(itemView);

            filmName = itemView.findViewById(R.id.film_title);
            filmRating = itemView.findViewById(R.id.film_rating_star);
            filmImage = itemView.findViewById(R.id.film_image);

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
    public void onBindViewHolder(@NonNull final FilmViewHolder holder, final int position) {

        //set the text and images of the film poster by viewholder position
        holder.filmName.setText(mFilms.get(holder.getAdapterPosition()).getFilmTitle());
        holder.filmRating.setText(String.valueOf(mFilms.get(holder.getAdapterPosition())
                .getVoteAverage()));
        Picasso.get().load(BASE_IMAGE_URL + mFilms.get(holder.getAdapterPosition())
                .getPosterPath())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.filmImage);


        //set on click listener to view holder, pass serialized film object
        // with intent to detail activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, FilmDetailActivity.class);
                intent.putExtra("Film", mFilms.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returns the number of objects to generate viewholders for
        return mFilms.size();
    }
}
