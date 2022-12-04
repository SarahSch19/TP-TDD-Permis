package fr.esgi.cleancode.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SocialSecurityNumberValidatorTest {


    @Test
    public void shouldNotValidateSocialSecurityNumberNull () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(null) ;
        Assertions.assertFalse(socialSecurityNumberIsValid);
    }


    @Test
    public void shouldNotValidateSocialSecurityNumberEmpty () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber("") ;
        Assertions.assertFalse(socialSecurityNumberIsValid);
    }

    @Test
    public void shouldNotValidateSocialSecurityNumberTooShort () {
        String socialSecurityNumberShort = "12345678901234";
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(socialSecurityNumberShort) ;
        Assertions.assertFalse(socialSecurityNumberIsValid);
    }

    @Test
    public void shouldNotValidateSocialSecurityNumberTooLong () {
        String socialSecurityNumberLong = "1234567890123456";
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(socialSecurityNumberLong) ;
        Assertions.assertFalse(socialSecurityNumberIsValid);
    }

    @Test
    public void shouldNotValidateSocialSecurityNumberWithLetter () {
        String socialSecurityNumberWithLetter = "1234567abc12345";
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(socialSecurityNumberWithLetter) ;
        Assertions.assertFalse(socialSecurityNumberIsValid);
    }

    @Test
    public void shouldValidateSocialSecurityNumber () {
        String validSocialSecurityNumber = "123456789012345";
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(validSocialSecurityNumber) ;
        Assertions.assertTrue(socialSecurityNumberIsValid);
    }
}
