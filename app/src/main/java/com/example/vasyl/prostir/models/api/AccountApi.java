package com.example.vasyl.prostir.models.api;

import com.example.vasyl.prostir.models.Accounts;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountApi {

    @GET("b/5c38dfda2c87fa27306ca955")
    Call<Accounts> getAccounts();

}
