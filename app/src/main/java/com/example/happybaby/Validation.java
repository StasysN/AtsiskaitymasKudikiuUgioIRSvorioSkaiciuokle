package com.example.happybaby;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String USERNAME_PATERN = "^[A-ZąčęėįšųūaĄČĘĖĮŠŲŪ_-z]{3,20}$";
    private static final String PASSWORD_PATERN = "^[A-ZąčęėįšųūaĄČĘĖĮŠŲŪ.!?_-z0-9]{3,20}$";

    public static boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATERN);
        Matcher matcher = pattern.matcher(username);

        return matcher.find();
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATERN);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
