package com.isever.sergn.homeproject.interfaces;

import com.isever.sergn.homeproject.model.WeatherRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather(@Query("q") String cityCountry, @Query("appid") String keyApi);
}
