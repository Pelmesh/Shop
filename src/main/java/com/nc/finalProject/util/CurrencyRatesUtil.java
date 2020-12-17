package com.nc.finalProject.util;

import com.nc.finalProject.model.Currency;
import com.nc.finalProject.service.CurrencyService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@EnableScheduling
public class CurrencyRatesUtil {

    @Autowired
    private CurrencyService currencyService;

    private final String[] curAbbreviation = {"USD", "EUR"};
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Scheduled(cron = "0 0 9 * * *")
    private void getRate() {
        for (String curAbbreviation : curAbbreviation) {
            try {
                String line;
                String result = "";
                URL url = new URL("https://www.nbrb.by/api/exrates/rates/" + curAbbreviation + "?parammode=2");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = rd.readLine()) != null) {
                    result += line;
                }
                rd.close();
                saveRate(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveRate(String result) {
        JSONObject object = new JSONObject(result);
        String abbreviation = object.optString("Cur_Abbreviation");
        String rate = object.optString("Cur_OfficialRate");
        String date = object.optString("Date");
        Currency currency = currencyService.findByAbbreviation(abbreviation);
        if (currency == null) {
            currency = new Currency();
        }
        currency.setAbbreviation(abbreviation);
        currency.setRate(Double.parseDouble(rate));
        LocalDateTime timeBank = LocalDateTime.parse(date, FORMATTER);
        LocalDateTime timeNow = LocalDateTime.now();
        currency.setDateBank(timeBank);
        currency.setDateSave(timeNow);
        currencyService.save(currency);
    }

}
