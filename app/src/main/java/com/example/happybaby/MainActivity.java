package com.example.happybaby;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Baby> measurement;

    private BabyDAO measurementsDAO;

    Button choseGender;

    Button add;

    PopupWindow popup;

    ListView listView;

    CustomMeasurementsList customMeasurementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        measurementsDAO = new BabyDAO(getApplicationContext());

        add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPopup();
            }
        });


    }


    private void addPopup() {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.edit_popup,
                (ViewGroup) this.findViewById(R.id.popup));
        popup = new PopupWindow(layout, 600, 670, true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText age = layout.findViewById(R.id.edit_age);
        final EditText height = layout.findViewById(R.id.edit_height);
        final EditText weight = layout.findViewById(R.id.edit_weight);
        Button save = layout.findViewById(R.id.save_popup);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getage=age.getText().toString();
                String getheight=height.getText().toString();
                String getweight=weight.getText().toString();
                Baby measurements = new Baby(Double.parseDouble(getage), Double.parseDouble(getheight), Double.parseDouble(getweight) );
                measurementsDAO.create(measurements);

                if(customMeasurementsList==null){
                    customMeasurementsList =new CustomMeasurementsList((Activity) getApplicationContext(),
                            measurement, measurementsDAO);
                    listView.setAdapter(customMeasurementsList);
                }
                ArrayList<Baby> weathers1 = (ArrayList<Baby>) measurementsDAO.readAll();
                customMeasurementsList.setMeasurements(weathers1);

                ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
                popup.dismiss();
            }
        });
    }
}