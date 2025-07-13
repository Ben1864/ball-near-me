package com.bnm.clifrontend.client;


import com.bnm.clifrontend.model.CourtEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

}
