package fr.esgi.cleancode.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SocialSecurityNumberValidatorTest {
    private final String socialSecurityNumberShort = "12345678901234" ;
    private final String socialSecurityNumberLong = "1234567890123456" ;
    private final String socialSecurityNumberWithLetter = "1234567abc12345" ;
    private final String validSocialSecurityNumber = "123456789012345" ;


    @Test
    public void shouldNotValidateSocialSecurityNumberNull () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(null) ;
        Assertions.assertEquals(false, socialSecurityNumberIsValid);
    }


    @Test
    public void shouldNotValidateSocialSecurityNumberEmpty () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber("") ;
        Assertions.assertEquals(false, socialSecurityNumberIsValid);
    }

    @Test
    public void shouldNotValidateSocialSecurityNumberTooShort () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(socialSecurityNumberShort) ;
        Assertions.assertEquals(false, socialSecurityNumberIsValid);
    }

    @Test
    public void shouldNotValidateSocialSecurityNumberTooLong () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(socialSecurityNumberLong) ;
        Assertions.assertEquals(false, socialSecurityNumberIsValid);
    }

    @Test
    public void shouldNotValidateSocialSecurityNumberWithLetter () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(socialSecurityNumberWithLetter) ;
        Assertions.assertEquals(false, socialSecurityNumberIsValid);
    }

    @Test
    public void shouldValidateSocialSecurityNumber () {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(validSocialSecurityNumber) ;
        Assertions.assertEquals(true, socialSecurityNumberIsValid);
    }
}
