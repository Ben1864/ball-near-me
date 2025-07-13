package com.bnm.clifrontend.models;

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
