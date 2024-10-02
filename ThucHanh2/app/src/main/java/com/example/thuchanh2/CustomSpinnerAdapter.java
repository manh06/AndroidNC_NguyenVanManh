package com.example.thuchanh2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<Country> {
    private Context context;
    private List<Country> countryList;

    public CustomSpinnerAdapter(Context context, List<Country> countries) {
        super(context, 0, countries);
        this.context = context;
        this.countryList = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(final int position, View convertView, ViewGroup parent) {
        final View view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_row, parent, false);

        ImageView flagImageView = view.findViewById(R.id.flagImageView);
        TextView countryTextView = view.findViewById(R.id.countryTextView);
        Button removeButton = view.findViewById(R.id.removeButton);

        Country country = countryList.get(position);

        countryTextView.setText(country.getName());
        flagImageView.setImageResource(country.getFlagResource());

        removeButton.setOnClickListener(v -> {
            countryList.remove(position);
            notifyDataSetChanged(); // Cập nhật lại Spinner
        });

        return view;
    }
}

