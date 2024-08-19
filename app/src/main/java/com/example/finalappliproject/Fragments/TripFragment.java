package com.example.finalappliproject.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalappliproject.Adapters.TripListAdapter;
import com.example.finalappliproject.Interfaces.DataRetrievedListener;
import com.example.finalappliproject.Interfaces.TripCallback;
import com.example.finalappliproject.Models.Trip;
import com.example.finalappliproject.R;
import com.example.finalappliproject.Utilitis.Constants;
import com.example.finalappliproject.Utilitis.DataManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TripFragment extends Fragment {
    private RecyclerView main_LST_trips;

    String valueOfCategory;
    TripListAdapter tripListAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Trip> tripList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trip_fragment, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            valueOfCategory = bundle.getString("selectedCategory");
        }
        DataManager.getInstance().uploadTripsToDB();
        DataManager.getInstance().readFromDB(valueOfCategory, new DataRetrievedListener() {
            @Override
            public void onDataRetrieved(ArrayList<Trip> trips) {
                tripList = trips;
                initViews();
            }
        });

        // Find and initialize your views here

        findViews(rootView);

        return rootView;
    }

    private void initViews() {


        tripListAdapter = new TripListAdapter(getContext(), tripList);
        if(Objects.equals(valueOfCategory, "AllTrips"))
            tripListAdapter = new TripListAdapter(getContext(), DataManager.getAllTrips());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_trips.setLayoutManager(linearLayoutManager);
        main_LST_trips.setAdapter(tripListAdapter);

        tripListAdapter.setTripCallback(new TripCallback() {
            @Override
            public void favoriteClicked(Trip trip, int position) {
                trip.setFavorite((!trip.isFavorite()));
                DataManager.getInstance().updateTrip(valueOfCategory, trip);
                Objects.requireNonNull(main_LST_trips.getAdapter()).notifyItemChanged(position);
            }

            @Override
            public void itemClicked(Trip trip, int position) {
                // here i can change things per click on each item in the recycle
                Toast.makeText(getContext(), "" + trip.getTitle(), Toast.LENGTH_SHORT).show();
                // Create a new instance of the destination fragment
                TripDetailsFragment fragment = new TripDetailsFragment();

                // Create a bundle and add the trip object as an argument
                Bundle args = new Bundle();
                args.putParcelable("selectedTrip", trip);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();


            }
        });
    }

    private void findViews(View rootView) {
        main_LST_trips = rootView.findViewById(R.id.main_LST_trips);
    }


    private void readFromDB1(String category) {

        DocumentReference docRef = db.collection(Constants.DBKeys.TRIPS).document(category);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        List<Map<String, Object>> tripDataList = (List<Map<String, Object>>) document.get(category);
                        if (tripDataList != null) {
                            for (Map<String, Object> tripData : tripDataList) {
                                String title = (String) tripData.get("title");
                                String image = (String) tripData.get("image");
                                Boolean isFavorite = (Boolean) tripData.get("isFavorite");
                                String difficulty = (String) tripData.get("difficulty");
                                Integer preparation_time = (Integer) tripData.get("preparation_time");
                                String tripFeatures = (String) tripData.get("tripFeatures");


                                // Extract other fields as needed

                                Trip trip = new Trip(title, image, isFavorite, difficulty, preparation_time, tripFeatures);
                                tripList.add(trip);
                            }
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }

                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }





}

