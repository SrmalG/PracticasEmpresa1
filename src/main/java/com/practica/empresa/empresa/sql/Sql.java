package com.practica.empresa.empresa.sql;

public class Sql {

    private Sql() {}

    public static final String UPDATE_LAST_LOGIN = ("""
        UPDATE User u
        SET u.lastLogin = :lastLogin
        WHERE u.username = :username
    """);

    public static final String ERASE_USER = "Delete from User u where u.username = :username";
}

