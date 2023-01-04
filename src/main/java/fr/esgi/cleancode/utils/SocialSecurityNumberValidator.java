package fr.esgi.cleancode.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SocialSecurityNumberValidator {
    public static boolean validateSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber == null) return false ;
        Pattern socialSecurityNumberPattern = Pattern.compile("^\\d{15}$");
        Matcher matcher = socialSecurityNumberPattern.matcher(socialSecurityNumber);
        return matcher.find();
    }
}
