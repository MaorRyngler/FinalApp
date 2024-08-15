package com.example.finalappliproject.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalappliproject.Interfaces.DataRetrievedListener;
import com.example.finalappliproject.Models.Trip;
import com.example.finalappliproject.Utilitis.DataManager;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<ArrayList<Trip>> mTrips;

    public HomeViewModel() {
        mTrips = new MutableLiveData<>();
        DataManager.getInstance().readFromDBUserFavorites(new DataRetrievedListener() {
            @Override
            public void onDataRetrieved(ArrayList<Trip> trips) {
                mTrips.setValue(trips);
            }
        });
    }

    public LiveData<ArrayList<Trip>> getTrips() {
        return mTrips;
    }

}