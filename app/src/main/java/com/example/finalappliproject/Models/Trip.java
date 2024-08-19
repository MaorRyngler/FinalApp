package com.example.finalappliproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip implements Parcelable {

    private String title = "";
    private String image = "";
    private boolean favorite = false;
    private String difficulty = "";
    private int time = 0;
    private String tripFeatures = "";

    public Trip(String title, String image, Boolean favorite, String difficulty, Integer time, String tripFeatures) {
        this.title = title;
        this.image = image;
        this.favorite = favorite;
        this.difficulty = difficulty;
        this.time = time;
        this.tripFeatures = tripFeatures;
    }



    public Trip(String title, String image, String difficulty, int time, String tripFeatures) {
        this.title = title;
        this.image = image;
        this.difficulty = difficulty;
        this.time = time;
        this.tripFeatures = tripFeatures;
    }

    public Trip() {

    }


    public String getTripFeatures() {
        return tripFeatures;
    }

    public Trip setTripFeatures(String tripFeatures) {
        this.tripFeatures = tripFeatures;
        return this;
    }

    public Trip(String title, String image, String difficulty, int time) {
        this.title = title;
        this.image = image;
        this.difficulty = difficulty;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public Trip setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Trip setImage(String image) {
        this.image = image;
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public Trip setFavorite(boolean isFavorite) {
        this.favorite = isFavorite;
        return this;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Trip setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public int getTime() {
        return time;
    }

    public Trip setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", isFavorite=" + favorite +
                ", difficulty='" + difficulty + '\'' +
                ", time=" + time +
                '}';
    }

    // Parcelable.Creator
    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    protected Trip(Parcel in) {
        title = in.readString();
        image = in.readString();
        favorite = in.readByte() != 0;  // Read a byte and convert it to a boolean
        difficulty = in.readString();
        time = in.readInt();
        tripFeatures = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
        dest.writeByte((byte) (favorite ? 1 : 0));  // Write a byte representing the boolean value
        dest.writeString(difficulty);
        dest.writeInt(time);
        dest.writeString(tripFeatures);
    }

}
