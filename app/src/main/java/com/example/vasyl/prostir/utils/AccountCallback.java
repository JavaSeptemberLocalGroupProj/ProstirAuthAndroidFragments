package com.example.vasyl.prostir.utils;

import com.example.vasyl.prostir.models.Account;

import java.util.List;

public interface AccountCallback {

    void setAccountList(List<Account> accountList);
    void setError(String errorMessage);

}
