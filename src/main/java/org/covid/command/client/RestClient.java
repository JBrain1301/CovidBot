package org.covid.command.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RestClient {
    private OkHttpClient client = new OkHttpClient();

    public String requestCountryByName(String countryName) {
        System.out.println(countryName);
        Request request = new Request.Builder()
                .url("https://covid-19-data.p.rapidapi.com/country?format=json&name=" + countryName)
                .get()
                .addHeader("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "db0e410431msh936e6d399faa361p1a1d70jsn02185e9e0340")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
