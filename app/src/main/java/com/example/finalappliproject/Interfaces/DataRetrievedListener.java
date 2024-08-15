package com.example.finalappliproject.Interfaces;

import com.example.finalappliproject.Models.Trip;
import java.util.ArrayList;


public interface DataRetrievedListener {

    void onDataRetrieved(ArrayList<Trip> trips);

}