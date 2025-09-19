package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listycitylab3.AddCityFragment;
import com.example.listycitylab3.City;
import com.example.listycitylab3.CityArrayAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};
        // the samples cities

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);
        // shows the city, and it links it with the rows under "city"


        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v ->
                new AddCityFragment().show(getSupportFragmentManager(), "Add City")
        );
        // this is the add button function, to add a city


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City clicked_City = dataList.get(position);
            AddCityFragment fragment = AddCityFragment.newInstance(clicked_City, position);
            fragment.show(getSupportFragmentManager(), "Edit City");
        });
        // this is when you click a city, it shows the idea to edit the city
    }

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
    //add a new city

    @Override
    public void editCity(City city, int position) {
        dataList.set(position, city);
        cityAdapter.notifyDataSetChanged();
    }
    // to edit city, when clicked
}
