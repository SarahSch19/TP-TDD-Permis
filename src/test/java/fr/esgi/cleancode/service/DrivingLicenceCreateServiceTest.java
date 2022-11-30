package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

public class DrivingLicenceCreateServiceTest {

    @Mock
    private final InMemoryDatabase db = InMemoryDatabase.getInstance();

    @Mock
    private final CreateDrivingLicenceService createService = CreateDrivingLicenceService.getInstance() ;

    private DrivingLicence validDrivingLicence ;

    @BeforeEach
    public void initValidDrivingLicence () {
        final var id = UUID.randomUUID();
        final var validSocialSecurityNumber = "123456789012345" ;
        validDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(validSocialSecurityNumber)
                .build();
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberNull () {
        final var socialSecurityNumberShort = null ;
        final var id = UUID.randomUUID();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(() -> {
            createService.save(validDrivingLicence) ;
        }, InvalidDriverSocialSecurityNumberException.class) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberEmpty () {
        final var socialSecurityNumberShort = "" ;
        final var id = UUID.randomUUID();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(() -> {
            createService.save(validDrivingLicence) ;
        }, InvalidDriverSocialSecurityNumberException.class) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberTooShort () {
        final var socialSecurityNumberShort = "12345678901234" ;
        final var id = UUID.randomUUID();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(() -> {
            createService.save(validDrivingLicence) ;
        }, InvalidDriverSocialSecurityNumberException.class) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberTooLong () {
        final var socialSecurityNumberShort = "1234567890123456" ;
        final var id = UUID.randomUUID();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(() -> {
            createService.save(validDrivingLicence) ;
        }, InvalidDriverSocialSecurityNumberException.class) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberWithLetter () {
        final var socialSecurityNumberShort = "1234567abc1234" ;
        final var id = UUID.randomUUID();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(() -> {
            createService.save(validDrivingLicence) ;
        }, InvalidDriverSocialSecurityNumberException.class) ;
    }

    @Test
    public void shouldNotThrowIfSocialSecurityNumberValid () {
        final var socialSecurityNumberShort = "123456789012345" ;
        final var id = UUID.randomUUID();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertDoesNotThrow(() -> {
            createService.save(validDrivingLicence) ;
        }) ;
    }
    
    @Test
    public void shouldInsertAndReturnDrivingLicence() {
        var createdDrivingLicence = createService.save(validDrivingLicence) ;
        Assertions.assertEquals(db.findById(validDrivingLicence.getId()), createdDrivingLicence);
    }

}
