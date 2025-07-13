package com.bnm.clifrontend.clients;

import com.bnm.clifrontend.models.GoogleGeocodingResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GeocodingClient {

    @GetExchange("/geocode/json")
    GoogleGeocodingResponse geocode(@RequestParam("address") String address,@RequestParam("key") String apiKey);
}
