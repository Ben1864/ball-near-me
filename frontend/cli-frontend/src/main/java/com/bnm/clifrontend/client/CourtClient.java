package com.bnm.clifrontend.client;


import com.bnm.clifrontend.model.CourtEntity;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;


public interface CourtClient {

    @GetExchange("/api/courts")
    List<CourtEntity> getCourts();

}
