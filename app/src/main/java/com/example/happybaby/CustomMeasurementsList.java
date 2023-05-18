package com.example.happybaby;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMeasurementsList extends BaseAdapter {

    private Activity context;

    private ArrayList<Baby> measurements;

    private PopupWindow popup;

    private BabyDAO measurementsDAO;

    public CustomMeasurementsList(Activity context, ArrayList<Baby> measurements, BabyDAO measurementsDAO) {
        this.context = context;
        this.measurements = measurements;
        this.measurementsDAO = measurementsDAO;
    }
    public void setMeasurements(ArrayList<Baby> measurements) {
        this.measurements = measurements;
    }

    public static class ViewHolder {
        TextView textViewId;
        TextView textViewAge;
        TextView textViewHeight;
        TextView textViewWeight;
        TextView textViewHeightAverage;
        TextView textViewWeightAverage;
        Button editButton;
        Button deleteButton;
    }

    @Override
    public int getCount() {
        return measurements.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);

            holder.textViewId = row.findViewById(R.id.id);
            holder.textViewAge = row.findViewById(R.id.age);
            holder.textViewHeight = row.findViewById(R.id.height);
            holder.textViewWeight = row.findViewById(R.id.weight);
            holder.textViewHeightAverage = row.findViewById(R.id.height_average);
            holder.textViewWeightAverage = row.findViewById(R.id.weight_average);
            holder.editButton = row.findViewById(R.id.edit_button);
            holder.deleteButton = row.findViewById(R.id.delete_button);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewId.setText("" + measurements.get(position).getId());
        holder.textViewAge.setText("" + measurements.get(position).getBabyAge());
        holder.textViewHeight.setText("" + measurements.get(position).getBabyHeight());
        holder.textViewWeight.setText("" + measurements.get(position).getBabyWeight());
        holder.textViewHeightAverage.setText(measurements.get(position).getBabyHeightAverage());
        holder.textViewWeightAverage.setText(measurements.get(position).getBabyWeightAverage());

        final int positionPopup = position;

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPopup(positionPopup);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurementsDAO.delete(measurements.get(positionPopup));
                measurements = (ArrayList<Baby>) measurementsDAO.readAll();
                notifyDataSetChanged();
            }
        });


        return row;
    }

    private void editPopup(final int positionPopup) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.edit_popup,
                (ViewGroup) context.findViewById(R.id.popup));
        popup = new PopupWindow(layout, 600, 800, true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText age = layout.findViewById(R.id.edit_age);
        final EditText height = layout.findViewById(R.id.edit_height);
        final EditText weight = layout.findViewById(R.id.edit_weight);
        Button save = layout.findViewById(R.id.save_popup);
        age.setText("" + measurements.get(positionPopup).getBabyAge());
        height.setText("" + measurements.get(positionPopup).getBabyHeight());
        weight.setText("" + measurements.get(positionPopup).getBabyWeight());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getage = age.getText().toString();
                String getheight = height.getText().toString();
                String getweight = weight.getText().toString();
                Baby measurement = measurements.get(positionPopup);
                measurement.setBabyAge(Double.parseDouble(getage));
                measurement.setBabyHeight(Double.parseDouble(getheight));
                measurement.setBabyWeight(Double.parseDouble(getweight));

                measurementsDAO.update(measurement);
                measurements = (ArrayList<Baby>) measurementsDAO.readAll();
                notifyDataSetChanged();
                popup.dismiss();
            }
        });

    }
}
