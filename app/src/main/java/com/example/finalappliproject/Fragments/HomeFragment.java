package com.example.finalappliproject.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalappliproject.Adapters.TripListAdapter;
import com.example.finalappliproject.Interfaces.TripCallback;
import com.example.finalappliproject.Models.Trip;
import com.example.finalappliproject.R;
import com.example.finalappliproject.ViewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private RecyclerView main_LST_trips;

    private TripListAdapter tripListAdapter;
    private HomeViewModel homeViewModel;

    private Observer<ArrayList<Trip>> observer = new Observer<ArrayList<Trip>>() {
        @Override
        public void onChanged(ArrayList<Trip> trips) {
            tripListAdapter.updateTrips(trips);
        }
    };


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(root);
        initViews();

        return root;
    }

    private void initViews() {
        tripListAdapter = new TripListAdapter(getContext(),homeViewModel.getTrips().getValue()); // ממלא את הריסיקל
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_trips.setLayoutManager(linearLayoutManager);
        main_LST_trips.setAdapter(tripListAdapter);
        homeViewModel.getTrips().observe(getViewLifecycleOwner(), observer);

        tripListAdapter.setTripCallback(new TripCallback() {
            @Override
            public void favoriteClicked(Trip trip, int position) {
                trip.setFavorite((!trip.isFavorite()));
                Objects.requireNonNull(main_LST_trips.getAdapter()).notifyItemChanged(position);
            }

            @Override
            public void itemClicked(Trip trip, int position) {
                // here i can change things per click on each item in the recycle
                Toast.makeText(getContext(), "" + trip.getTitle(), Toast.LENGTH_SHORT).show();
                // Create a new instance of the destination fragment
                TripDetailsFragment fragment = new TripDetailsFragment();

                // Create a bundle and add the recipe object as an argument
                Bundle args = new Bundle();
                args.putParcelable("selectedRecipe", trip);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();



            }
        });
    }

    private void findViews(View rootView) {
        main_LST_trips = rootView.findViewById(R.id.main_LST_trips);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}