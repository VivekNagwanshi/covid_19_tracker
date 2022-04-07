package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<CountriesModel> {
    private Context context;
    private List<CountriesModel> countriesModelsList;
    private List<CountriesModel> countryModelsListFiltered;

    public Adapter(Context context, List<CountriesModel> countriesModelsList) {
        super(context, R.layout.list_coustom_item, countriesModelsList);
        this.context = context;
        this.countriesModelsList = countriesModelsList;
        this.countryModelsListFiltered = countriesModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coustom_item, null, true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);
        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(imageView);
        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public CountriesModel getItem(int position) {
        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countriesModelsList.size();
                    filterResults.values = countriesModelsList;

                } else {
                    List<CountriesModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for (CountriesModel itemsModel : countriesModelsList) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelsListFiltered = (List<CountriesModel>) results.values;
                Countries.countriesModelList = (List<CountriesModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
