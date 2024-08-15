package com.example.finalappliproject.Fragments.NavFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.finalappliproject.Fragments.HomeFragment;
import com.example.finalappliproject.Fragments.TripFragment;
import com.example.finalappliproject.R;
import com.google.android.material.imageview.ShapeableImageView;

public class MainFragment extends Fragment {

    Button BtAllTrips;
    ShapeableImageView BtTripInAsia;
    ShapeableImageView BtTripInEurope;
    ShapeableImageView BtTripInIsrael;
    ShapeableImageView BtLunch;
    Button BtFavorites;
    String Cat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        // Find and initialize your views here
        findViews(rootView);
        ButtonLogic();

        return rootView;
    }
    
    void findViews(View rootView){
        BtTripInAsia = rootView.findViewById(R.id.FridayDinner);
        BtTripInEurope = rootView.findViewById(R.id.BreakFast);
        BtTripInIsrael = rootView.findViewById(R.id.Dinner);
        BtLunch = rootView.findViewById(R.id.Lunch);
        BtFavorites = rootView.findViewById(R.id.favoriteButton);
        BtAllTrips = rootView.findViewById(R.id.allTripsButton);


    }

    void ButtonLogic(){

        BtAllTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the destination fragment
                TripFragment fragment = new TripFragment();
                Cat = "AllTrips";
                // Create a bundle and add the recipe object as an argument
                Bundle args = new Bundle();
                args.putString("selectedCategory", Cat);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });
        BtTripInAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the destination fragment
                TripFragment fragment = new TripFragment();
                Cat = "FridayDinner";
                // Create a bundle and add the recipe object as an argument
                Bundle args = new Bundle();
                args.putString("selectedCategory", Cat);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        BtTripInEurope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the destination fragment
                TripFragment fragment = new TripFragment();
                Cat = "BreakFast";
                // Create a bundle and add the recipe object as an argument
                Bundle args = new Bundle();
                args.putString("selectedCategory", Cat);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        BtTripInIsrael.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the destination fragment
                TripFragment fragment = new TripFragment();
                Cat = "Dinner";
                // Create a bundle and add the recipe object as an argument
                Bundle args = new Bundle();
                args.putString("selectedCategory", Cat);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        BtLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the destination fragment
                TripFragment fragment = new TripFragment();
                Cat = "Lunch";
                // Create a bundle and add the recipe object as an argument
                Bundle args = new Bundle();
                args.putString("selectedCategory", Cat);
                fragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });
        BtFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            }
        });


    }


}
