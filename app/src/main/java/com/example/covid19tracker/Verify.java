package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class Verify extends AppCompatActivity {

    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeath, tvTodayDeath, tvAffectedCountries, tvTests, tvTodayRecovered;
    ScrollView scrollView;
    SharedPreferences sharedPreferences;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeath = findViewById(R.id.tvTotalDeath);
        tvTodayDeath = findViewById(R.id.tvTodayDeath);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);
        tvTests = findViewById(R.id.tvTests);
        tvTodayRecovered = findViewById(R.id.tvTodayRecovered);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("flag", "verify");
        myEdit.apply();
        myEdit.commit();
        fetchData();
    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/all";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeath.setText(jsonObject.getString("deaths"));
                            tvTodayDeath.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));
                            tvTests.setText(jsonObject.getString("tests"));
                            tvTodayRecovered.setText(jsonObject.getString("todayRecovered"));
                            PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
                            mPieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFC107")));
                            mPieChart.addPieSlice(new PieModel("Active", Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#56B7F1")));
                            mPieChart.addPieSlice(new PieModel("TotalDeath", Integer.parseInt(tvTotalDeath.getText().toString()), Color.parseColor("#FA0101")));
                            mPieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#39BA46")));
                            mPieChart.startAnimation();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(Verify.this, error.getMessage(), Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    public void goToTrackCountries(View view) {
        Intent intent = new Intent(this, Countries.class);
        startActivity(intent);
    }
}