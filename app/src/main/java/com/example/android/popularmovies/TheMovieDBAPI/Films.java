package com.example.android.popularmovies.TheMovieDBAPI;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;


//Implements parcelable to exchange information between activities
@Entity(tableName = "FilmsTable")
public class Films implements Parcelable {



    @SerializedName("vote_count") private int voteCount;
    @PrimaryKey @SerializedName("id") private int id;
    @SerializedName("vote_average") private double voteAverage;
    @SerializedName("title") private String filmTitle;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("backdrop_path") private String backdropPath;
//    @SerializedName("genre_ids") private int[] genreIDs;
    @SerializedName("overview") private String overview;
    @SerializedName("release_date") private String releaseDate;




    public Films(int voteCount, int id, double voteAverage, String filmTitle, String posterPath,
                 String backdropPath, String overview, String releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.filmTitle = filmTitle;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }



    //read and right attributes in the order listed in Films object
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(voteCount);
        out.writeInt(id);
        out.writeDouble(voteAverage);
        out.writeString(filmTitle);
        out.writeString(posterPath);
        out.writeString(backdropPath);
        out.writeString(overview);
        out.writeString(releaseDate);
    }

    //constructor for parcel
    public Films(Parcel in) {
        voteCount = in.readInt();
        id = in.readInt();
        voteAverage = in.readDouble();
        filmTitle = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }

    //parcel creator method
    public static final Parcelable.Creator<Films> CREATOR
            = new Parcelable.Creator<Films>() {
        public Films createFromParcel(Parcel in) {
            return new Films(in);
        }

        public Films[] newArray(int size) {
            return new Films[size];
        }
    };

    public int describeContents() {
        return 0;
    }


    //Getters
    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


}
