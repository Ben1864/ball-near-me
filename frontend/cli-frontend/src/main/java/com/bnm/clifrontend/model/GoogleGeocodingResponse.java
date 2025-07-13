package com.bnm.clifrontend.model;

import java.util.List;

public record GoogleGeocodingResponse (List<Result> results, String status) {}

