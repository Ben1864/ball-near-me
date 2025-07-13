package com.bnm.clifrontend.clients;


import com.bnm.clifrontend.models.CourtEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;


public interface CourtClient {

    @GetExchange("/api/courts")
    List<CourtEntity> getCourts();

    @GetExchange("/api/courts/{id}")
    CourtEntity getCourtById(@PathVariable("id") String id);

    @PostExchange("/api/courts")
    CourtEntity addCourt(@RequestBody CourtEntity court);

    @GetExchange("/api/courts/nearby")
    List<CourtEntity> getNearbyCourts(@RequestParam double lat, @RequestParam double lng,
                                      @RequestParam(defaultValue = "10") int limit);

}
