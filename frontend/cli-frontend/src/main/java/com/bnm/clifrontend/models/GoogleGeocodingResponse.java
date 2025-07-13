package com.bnm.clifrontend.models;

import java.util.List;

public record GoogleGeocodingResponse (List<Result> results, String status) {}

