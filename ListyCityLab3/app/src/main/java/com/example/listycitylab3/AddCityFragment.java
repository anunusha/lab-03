package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.listycitylab3.City;

public class AddCityFragment extends DialogFragment {

    interface AddCityDialogListener {
        void addCity(City city);
        void editCity(City city, int position);
    }

    private AddCityDialogListener listener;

    // Keys for arguments
    private static final String ARG_CITY = "city";
    private static final String ARG_POSITION = "position";

    // Factory method to create fragment for editing
    public static AddCityFragment newInstance(City city, int position) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View inflated = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_add_city, null);

        EditText editCityName = inflated.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = inflated.findViewById(R.id.edit_text_province_text);

        City city = null;
        int position = -1;

        // If editing, pre-fill text fields
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable(ARG_CITY);
            position = getArguments().getInt(ARG_POSITION, -1);
            if (city != null) {
                editCityName.setText(city.getName());
                editProvinceName.setText(city.getProvince());
            }
        }

        City finalCity = city;
        int finalPosition = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(inflated)
                .setTitle(finalCity != null ? "Edit City" : "Add City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(finalCity != null ? "Save" : "Add", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    if (finalCity != null && finalPosition != -1) {
                        finalCity.setName(cityName);
                        finalCity.setProvince(provinceName);
                        listener.editCity(finalCity, finalPosition);
                    } else {
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }
}
