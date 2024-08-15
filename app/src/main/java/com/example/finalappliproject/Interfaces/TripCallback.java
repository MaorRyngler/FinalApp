package com.example.finalappliproject.Interfaces;

import com.example.finalappliproject.Models.Trip;

public interface TripCallback {

    void favoriteClicked(Trip trip, int position);

    void itemClicked(Trip trip, int position);
}
