package com.example.vasyl.prostir.data;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;

import com.example.vasyl.prostir.models.Account;
import com.example.vasyl.prostir.models.Device;
import com.example.vasyl.prostir.models.api.AccountApi;
import com.example.vasyl.prostir.models.repository.AccountRepository;
import com.example.vasyl.prostir.utils.AccountCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceReference {


    // This final variables must to be changed to non-final when server will work.

    private final static String PASSWORD = "0123"; // Password from server
    private final static String PHONENUMBER = "6505551212"; // Phone from server
    private final static String TOKEN = "25"; // Token from server
    private final static String codeForAccount = "BLPONB - 789"; // Code for Account from Server

    private static String userPhoneNumber; // User phone number
    private static String Description_Temp; // Temp variable for Description of Account
    private static String userPasswordForApp = PASSWORD; // Password for enter to app (CHANGE)
    private static boolean AllowMultipleDevices; // Allow mupltiple devices variablwe to server

    private static ArrayList<HashMap<String, String>> arrayListOfAccounts = new ArrayList<>(); // List of Accounts for ListView
    private static ArrayList<Device> deviceList = new ArrayList<Device>(); // List of Devices
    private static List<Account> accounts = new ArrayList<>(); // List of accounts from DataBase

    public static AccountApi getAccountApi() {

        return new Retrofit.Builder()
                .baseUrl("https://api.jsonbin.io/ ")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AccountApi.class);
    }

    public static void connectToServer() {

        new AccountRepository(getAccountApi()).getAccountsList(new AccountCallback() {
            @Override
            public void setAccountList(List<Account> accountList) {
                accounts.addAll(accountList);
                System.out.println("Hello World");
                initDataBase();
            }

            @Override
            public void setError(String errorMessage) {
                // TODO
            }
        });
    }

    public static void initDataBase() { // First initialization of Data

        // Accounts
        HashMap<String, String> map;

        for (Account item : accounts) {
            map = new HashMap<>();
            System.out.println(item.getService());
            map.put("Name", item.getService());
            map.put("Token", item.getTotpCode());
            arrayListOfAccounts.add(map);
        }

        for (Account item : accounts)
        {
            for (Device device : item.getDevices()) {
                deviceList.add(new Device(device.getDeviceType(), device.getDeviceName(),  device.getLastUseDate()));
            }
        }

    }

    public static boolean isRegisteredDevice() { // This method checks whether the current device is registered.
        // TODO
        return true;
    }

    public static void addCurrentToDeviceList() { // This method adds current Device to Device List
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = Build.MANUFACTURER + " " + Build.MODEL;

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy ");
        Date date = new Date();
        String todayDate = dateFormat.format(date);

        try {
            deviceName += " " + myDevice.getName();
        } catch (Exception e) {
            // TODO
        }

        deviceList.add(new Device("0", deviceName, todayDate));
    }

    public static void sendSMSAgain() {
        // TODO
    }

    public static void makeCallAgain() {
        // TODO
    }

    public static ArrayList<HashMap<String, String>> getArrayListOfAccounts() {

        return arrayListOfAccounts;
    }

    public static void addItemToArrayListOfAccounts(String name, String token) {

        HashMap<String, String> map;

        map = new HashMap<>();
        map.put("Name", name);
        map.put("Token", token);

        arrayListOfAccounts.add(map);
    }

    public static ArrayList<Device> getDeviceList() {

        return deviceList;
    }

    public static String getDescription_Temp() {
        return Description_Temp;
    }

    public static void setDescription_Temp(String value) {
        Description_Temp = value;
    }

    public static String getUserPhoneNumber() {

        return userPhoneNumber;
    }

    public static void setUserPhoneNumber(String value) {

        userPhoneNumber = value;
    }

    public static String getPassword() {

        return PASSWORD;
    }

    public static String getPhoneNumber() {

        return PHONENUMBER;
    }

    public static String getToken() {

        return TOKEN;
    }

    public static boolean isAllowMultipleDevices() {

        return AllowMultipleDevices;
    }

    public static void setAllowMultipleDevices(boolean value) {

        AllowMultipleDevices = value;
    }

    public static String getCodeForAccount() {

        return codeForAccount;
    }

    public static String getUserPasswordForApp() {

        return userPasswordForApp;
    }

    public static void setUserPasswordForApp() {
        // TODO
    }

}

