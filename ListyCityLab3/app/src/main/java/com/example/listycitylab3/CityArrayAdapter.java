package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listycitylab3.City;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {

    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view;

        if (convertView == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.content, parent, false);
        } else {
            view = convertView;
        }

        City city = getItem(position);

        TextView city_name = view.findViewById(R.id.city_text);
        TextView province_name = view.findViewById(R.id.province_text);

        city_name.setText(city.getName());
        province_name.setText(city.getProvince());

        return view;
    }
    // get the name for the city , and sets it to the way that it needs to be portrayed
}
