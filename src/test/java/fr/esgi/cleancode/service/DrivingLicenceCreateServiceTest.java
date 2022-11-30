package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

public class DrivingLicenceCreateServiceTest {

    @Mock
    private final InMemoryDatabase db = InMemoryDatabase.getInstance();

    @Mock
    private final CreateDrivingLicenceService createService = CreateDrivingLicenceService.getInstance() ;

    private DrivingLicence validDrivingLicence ;

    @BeforeEach
    public void initValidDrivingLicence () {
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var validSocialSecurityNumber = "123456789012345" ;
        validDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(validSocialSecurityNumber)
                .build();
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberNull () {
        final String socialSecurityNumberShort = null ;
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(
                InvalidDriverSocialSecurityNumberException.class,
                () -> {
                    createService.save(invalidDrivingLicence) ;
                }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberEmpty () {
        final var socialSecurityNumberShort = "" ;
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberTooShort () {
        final var socialSecurityNumberShort = "12345678901234" ;
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberTooLong () {
        final var socialSecurityNumberShort = "1234567890123456" ;
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldThrowIfSocialSecurityNumberWithLetter () {
        final var socialSecurityNumberShort = "1234567abc1234" ;
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        final var invalidDrivingLicence = DrivingLicence.builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumberShort)
                .build();
        Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> {
            createService.save(invalidDrivingLicence) ;
        }) ;
    }

    @Test
    public void shouldNotThrowIfSocialSecurityNumberValid () {
        final var socialSecurityNumberShort = "123456789012345" ;
        final var id = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();
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
        Optional<DrivingLicence> createdDrivingLicence = Optional.ofNullable(createService.save(validDrivingLicence));
        Assertions.assertEquals(createdDrivingLicence, db.findById(validDrivingLicence.getId()));
    }

    @Test
    public void shouldInsertDrivingLicenceWith12Points() {
        Optional<DrivingLicence> createdDrivingLicence = Optional.ofNullable(createService.save(validDrivingLicence));
        Assertions.assertEquals(12, createdDrivingLicence.get().getAvailablePoints());
    }

}
