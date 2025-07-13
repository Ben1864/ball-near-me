package com.bnm.clifrontend.model;

public record CourtEntity(String id, String name, double latitude, double longitude) {
    @Override
    public String toString() {
        return "Court{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + latitude +
                ", lng=" + longitude +
                "}";
    }
}
