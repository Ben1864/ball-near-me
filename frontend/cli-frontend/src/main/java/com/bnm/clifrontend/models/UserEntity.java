package com.bnm.clifrontend.models;

public record UserEntity(String id, String name, String password, String email,
                         double latitude, double longitude) {
    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
