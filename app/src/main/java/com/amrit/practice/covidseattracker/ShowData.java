package com.amrit.practice.covidseattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {

    public static final String LOG_TAG = ShowData.class.getSimpleName();
    public ArrayList<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        Intent intent = getIntent();
        String response = intent.getStringExtra("value");

        ListView listView = findViewById(R.id.list_view);
        TextView textView = findViewById(R.id.error);
        textView.setVisibility(View.GONE);
        list = new ArrayList<>();

        try {
            JSONObject objectResponse = new JSONObject(response);
            JSONArray jsonArray = objectResponse.getJSONArray("sessions");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject individualObject = (JSONObject) jsonArray.get(i);

                int center_id = individualObject.getInt("center_id");
                String name = individualObject.getString("name");
                String address = individualObject.getString("address");
                boolean paid = !individualObject.getString("fee_type").equals("Free");
                String vaccine_name = individualObject.getString("vaccine");
                boolean age18 = individualObject.getInt("min_age_limit") == 18;
                int dose1 = individualObject.getInt("available_capacity_dose1");
                int dose2 = individualObject.getInt("available_capacity_dose2");

                Data data = new Data(center_id, name + address, paid, vaccine_name, age18, dose1, dose2);
                list.add(data);
            }

            if(list.size() > 0){
                ListAdapter listAdapter = new ListAdapter(this, list);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener((adapterView, view, position, l) -> {
                    DataDialog dialog = new DataDialog(list.get(position));
                    dialog.show(getSupportFragmentManager(), "dialog");
                });
            }else{
                listView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Input data is not JSON");
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }

    }
}