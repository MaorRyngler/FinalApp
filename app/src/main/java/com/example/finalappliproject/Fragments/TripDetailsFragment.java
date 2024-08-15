package com.example.finalappliproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.finalappliproject.Models.Trip;
import com.example.finalappliproject.R;
import com.example.finalappliproject.Utilitis.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;

public class TripDetailsFragment extends Fragment {

    TextView tripDetails;
    TextView tripName;
    ShapeableImageView tripPicture;
    Trip selectedTrip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.trip_details,container,false);
        findViews(root);



        Bundle args = getArguments();
        if (args != null) {
            selectedTrip = args.getParcelable("selectedTrip");
        }

        tripDetails.setText(selectedTrip.getTripFeatures());
        tripName.setText(selectedTrip.getTitle());
        ImageLoader.getInstance().loadImage(selectedTrip.getImage(), tripPicture);


        return root;
    }

    private void findViews(ViewGroup root) {
        tripDetails = root.findViewById(R.id.TripDetails);
        tripPicture = root.findViewById(R.id.TripImage);
        tripName = root.findViewById(R.id.TripName);
    }

}


