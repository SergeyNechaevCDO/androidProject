package com.isever.sergn.homeproject.controllers;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isever.sergn.homeproject.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final ListInteractor interactor;
    private List<PersonalData> persons;

    CityAdapter(List<PersonalData> persons, ListInteractor interactor) {
        this.persons = persons;
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, @SuppressLint("RecyclerView") final int i) {


        cityViewHolder.cityName.setText(persons.get(i).name);
        cityViewHolder.regionName.setText(persons.get(i).region);
        cityViewHolder.cityPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        LinearLayout linearLayout;
        TextView cityName;
        TextView regionName;
        ImageView cityPhoto;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.main_card);
            this.linearLayout = itemView.findViewById(R.id.main_layout_list);
            this.cityName = itemView.findViewById(R.id.main_city_name);
            this.regionName = itemView.findViewById(R.id.main_region_name);
            this.cityPhoto = itemView.findViewById(R.id.main_image);

            this.cardView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            interactor.onItemClicked(getLayoutPosition());
                        }
                    });
        }
    }

    public interface ListInteractor {
        void onItemClicked(int position);
    }
}

