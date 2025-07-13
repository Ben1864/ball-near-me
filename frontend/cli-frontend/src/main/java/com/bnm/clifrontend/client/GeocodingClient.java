package com.bnm.clifrontend.client;

import com.bnm.clifrontend.model.GoogleGeocodingResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GeocodingClient {

    @GetExchange("/geocode/json")
    GoogleGeocodingResponse geocode(@RequestParam("address") String address,@RequestParam("key") String apiKey);
}
