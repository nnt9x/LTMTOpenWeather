package com.example.ltmtopenweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText edtCity;
    private ImageView imgWeather;
    private TextView tvDescription;

    // Open Weather Service
    private OpenWeatherService openWeatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind Id
        edtCity = findViewById(R.id.edtCity);
        imgWeather = findViewById(R.id.imgWeather);
        tvDescription = findViewById(R.id.tvDescription);

        openWeatherService = new OpenWeatherService(this) {
            @Override
            protected void getCityWeatherSuccess(City city) {
                tvDescription.setText(city.getName()+", "+city.getDescription()+"*C");

                // Hình ảnh thời tiết
                String urlIcon = openWeatherService.buildIconURL(city.getIconWeather());
                Glide.with(MainActivity.this).load(urlIcon).into(imgWeather);

            }

            @Override
            protected void getCityWeatherFail() {
                Toast.makeText(MainActivity.this,"Không có dữ liệu",Toast.LENGTH_SHORT).show();
            }
        };

    }

    // Khi click vao tim kiem => chay ham nay
    public void searchCityByName(View view) {
        // Lay du lieu Edittext -> city -> tao ra URL
        String city = edtCity.getText().toString().trim();
        if(city.isEmpty()){
            edtCity.setError("Hãy nhập dữ liệu!");
            return;
        }
        // Tao Url
        String myURL = openWeatherService.buildRequestURL(city);

        // Tao request -> lay ket qua

        openWeatherService.getWeather(myURL);

    }
}