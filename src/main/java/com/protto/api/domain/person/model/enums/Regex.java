package com.protto.api.domain.person.model.enums;

public class Regex {

    private Regex(){
    }

    public static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    public static final String PASSWORD = "^(?=.{8,50}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$";

}