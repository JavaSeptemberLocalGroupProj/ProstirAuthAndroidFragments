package com.example.vasyl.prostir.models.repository;

import com.example.vasyl.prostir.models.Account;
import com.example.vasyl.prostir.models.Accounts;
import com.example.vasyl.prostir.models.api.AccountApi;
import com.example.vasyl.prostir.utils.AccountCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private AccountApi accountApi;

    public AccountRepository(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    public void getAccountsList(final AccountCallback callback) {
        accountApi.getAccounts().enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                List<Account> accountList = response.body().getAccounts();
                accountList.addAll(accountList);
                callback.setAccountList(accountList);
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {
                callback.setError(t.getMessage());
            }
        });
    }
}
