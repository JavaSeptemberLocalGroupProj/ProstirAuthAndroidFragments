package com.example.vasyl.prostir.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Account {

    @SerializedName("service")
    @Expose
    private String service;

    @SerializedName("totp_code")
    @Expose
    private String totpCode;

    @SerializedName("devices")
    @Expose
    private List<Device> devices;

    public Account(String service, String totpCode, List<Device> devices) {
        this.service = service;
        this.totpCode = totpCode;
        this.devices = devices;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTotpCode() {
        return totpCode;
    }

    public void setTotpCode(String totpCode) {
        this.totpCode = totpCode;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
