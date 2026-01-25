package com.practica.empresa.empresa.security;

public interface HashStrategy {

    String hash(final String hash);
    boolean check(final String password, final String hashed);
}
