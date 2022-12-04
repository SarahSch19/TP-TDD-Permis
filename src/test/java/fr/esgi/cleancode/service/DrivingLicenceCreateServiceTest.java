package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreateServiceTest {

    @InjectMocks
    private CreateDrivingLicenceService createService ;

    private DrivingLicence validDrivingLicence ;

    @BeforeEach
    public void initValidDrivingLicence () {
        final var drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var validSocialSecurityNumber = "123456789012345" ;
        validDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(validSocialSecurityNumber)
                .build();
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberNull () {
        final String socialSecurityNumberNull = null ;
        final var drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(socialSecurityNumberNull)
                .build();
        Assertions.assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> {
                    createService.save(invalidDrivingLicence) ;
                }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberEmpty () {
        final var socialSecurityNumberEmpty = "" ;
        final var drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(socialSecurityNumberEmpty)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberTooShort () {
        final var socialSecurityNumberShort = "12345678901234" ;
        final var drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberTooLong () {
        final var socialSecurityNumberLong = "1234567890123456" ;
        final var drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(socialSecurityNumberLong)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberWithLetter () {
        final var socialSecurityNumberWithLetter = "1234567abc1234" ;
        final var drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(socialSecurityNumberWithLetter)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldNotThrowIfSocialSecurityNumberValid () {
        Assertions.assertDoesNotThrow(() -> {
            createService.save(validDrivingLicence) ;
        }) ;
    }
    
    @Test
    public void shouldInsertAndReturnDrivingLicence() {
        DrivingLicence createdDrivingLicence = createService.save(validDrivingLicence);
        Assertions.assertEquals(validDrivingLicence, createdDrivingLicence);
    }

    @Test
    public void shouldInsertDrivingLicenceWith12Points() {
        Optional<DrivingLicence> createdDrivingLicence = Optional.ofNullable(createService.save(validDrivingLicence));
        Assertions.assertEquals(12, createdDrivingLicence.get().getAvailablePoints());
    }

}
