package com.bnm.clifrontend.model;

public record UserEntity(String id, String name, String password, String email,
                         double latitude, double longitude) {
}
