package com.example.finalappliproject.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalappliproject.Interfaces.TripCallback;
import com.example.finalappliproject.Models.Trip;
import com.example.finalappliproject.R;
import com.example.finalappliproject.Utilitis.DataManager;
import com.example.finalappliproject.Utilitis.ImageLoader;
import com.example.finalappliproject.Utilitis.TimeFormat;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.ItemViewHolder> {

        private Context context;
        private ArrayList<Trip> trips;
        private TripCallback tripCallback;

        private TripListAdapter.OnClickListener onClickListener;

        public TripListAdapter(Context context, ArrayList<Trip> tripItem) {
            this.context = context;
            this.trips = tripItem;
        }
        public void setTripCallback(TripCallback tripCallback) {
            this.tripCallback = tripCallback;
    }

        @NonNull
        @Override
        public TripListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            return itemViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TripListAdapter.ItemViewHolder holder, int position) {
            Trip trip = getItem(position);
            holder.difficulty.setText(trip.getDifficulty());
            holder.title.setText(trip.getTitle());
            holder.preparation_time.setText(TimeFormat.getFormattedTime(trip.getTime()));
            ImageLoader.getInstance().loadImage(trip.getImage(), holder.recipe_IMG_poster);
            if (trip.isFavorite()){
                holder.recipe_IMG_favorite.setImageResource(R.drawable.heart);
            }
            else{
                holder.recipe_IMG_favorite.setImageResource(R.drawable.heart_empty);
            }

        }
        public interface OnClickListener{
            void onClick(int position, Trip tripItem);
        }
        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

    public void updateTrips(ArrayList<Trip> trips) {
        this.trips = trips;
        notifyDataSetChanged();
    }

        @Override
        public int getItemCount() {
            return this.trips == null ? 0 : trips.size();
        }

        private Trip getItem(int position) {
            return this.trips.get(position);
        }


        public class ItemViewHolder extends RecyclerView.ViewHolder {
            private MaterialTextView title;
            private MaterialTextView difficulty;
            private MaterialTextView preparation_time;
            private ShapeableImageView recipe_IMG_poster;
            private ShapeableImageView recipe_IMG_favorite;

            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                recipe_IMG_poster = itemView.findViewById(R.id.trip_IMG_Image);
                title = itemView.findViewById(R.id.trip_LBL_title);
                difficulty = itemView.findViewById(R.id.trip_LBL_difficulty);
                preparation_time = itemView.findViewById(R.id.trip_LBL_duration);
                recipe_IMG_favorite = itemView.findViewById(R.id.trip_IMG_favorite);

                itemView.setOnClickListener(v -> {
                    if (tripCallback != null)
                        tripCallback.itemClicked(getItem(getAdapterPosition()), getAdapterPosition());
                });

                recipe_IMG_favorite.setOnClickListener(v -> {
                    Log.d("hi", "ItemViewHolder: hello");
                    if (tripCallback != null){
                        Trip trip = getItem(getAdapterPosition());
                        tripCallback.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition());
                        if(trip.isFavorite()){
                            DataManager.getInstance().addNewDocument(trip);
                        }
                        else {
                            DataManager.getInstance().deleteDocuments(trip);
                        }
                    }
                });

            }
        }

    }

//public interface OnClickListener{
//    void onClick(int position, Recipe recipeItem);
//}
//    public void setOnClickListener(OnClickListener onClickListener) {
//        this.onClickListener = onClickListener;
//    }