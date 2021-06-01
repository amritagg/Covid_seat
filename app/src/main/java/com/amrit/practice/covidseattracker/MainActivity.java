package com.amrit.practice.covidseattracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    EditText pincode;
    TextView dateText;
    ImageButton calenderIcon;
    Calendar calendar;
    Button search;
    int year, month, dayOfMonth;
    ProgressBar progressBar;
    String finalDate;
    final String baseURL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pincode = findViewById(R.id.pin_edit_text);
        dateText = findViewById(R.id.date_edit_text);
        calenderIcon = findViewById(R.id.calender_icon);
        search = findViewById(R.id.search);
        progressBar = findViewById(R.id.progress_bar);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calenderIcon.setOnClickListener(view -> {

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (datePicker, year, month, day) -> {
                        month++;
                        finalDate = day + "-" + month + "-" + year;
                        dateText.setText(finalDate);
                    }, year, month, dayOfMonth);
            datePickerDialog.show();

        });

        search.setOnClickListener(view -> {

            String pinString = pincode.getText().toString();
            if(pinString.length() != 6){
                Toast.makeText(this, "Wrong PINCODE !!!", Toast.LENGTH_SHORT).show();
            }else{

                if(dateText.getText() == null){
                    Toast.makeText(this, "Pls Select Date !!!", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    String url = baseURL + pincode.getText() + "&date=" + finalDate;
                    load(url);
                }
            }

        });
    }

    public void load(String url){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), ShowData.class);
                    intent.putExtra("value", response.toString());
                    startActivity(intent);
                },
                error -> {
                    Toast.makeText(getApplicationContext(), "Error while Loading the data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });

        queue.add(jsonObjectRequest);
    }

}