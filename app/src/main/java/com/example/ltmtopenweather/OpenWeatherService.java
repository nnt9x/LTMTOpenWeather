package com.example.ltmtopenweather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class OpenWeatherService {

    private Context context;
    private RequestQueue requestQueue;

    private final static String API_KEY = "928133397391e6af373468b74849e7ab";

    protected abstract void getCityWeatherSuccess(City city);
    protected abstract void getCityWeatherFail();

    public OpenWeatherService(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public String buildRequestURL(String city) {
        return String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=vi",
                city, API_KEY);
    }

    public String buildIconURL(String icon){
        return String.format("https://openweathermap.org/img/wn/%s@4x.png", icon);
    }

    public void getWeather(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            City city = new City();
                            String cityName = response.getString("name");
                            city.setName(cityName);

                            String icon = response.getJSONArray("weather")
                                    .getJSONObject(0).getString("icon");
                            city.setIconWeather(icon);

                            String temp = response.getJSONObject("main")
                                    .getString("temp");

                            city.setDescription(temp);

                            getCityWeatherSuccess(city);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getCityWeatherFail();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

}
