package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry, tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);
        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);

        tvCountry.setText(Countries.countriesModelList.get(positionCountry).getCountry());
        tvCases.setText(Countries.countriesModelList.get(positionCountry).getCases());
        tvRecovered.setText(Countries.countriesModelList.get(positionCountry).getRecovered());
        tvCritical.setText(Countries.countriesModelList.get(positionCountry).getCritical());
        tvActive.setText(Countries.countriesModelList.get(positionCountry).getActive());
        tvTodayCases.setText(Countries.countriesModelList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(Countries.countriesModelList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(Countries.countriesModelList.get(positionCountry).getTodayDeaths());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);

    }

    public void backBtn(View view) {
        finish();
    }
}
