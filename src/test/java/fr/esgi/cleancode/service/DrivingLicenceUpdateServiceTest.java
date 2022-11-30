package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class DrivingLicenceUpdateServiceTest {

    @InjectMocks
    private DrivingLicenceUpdateService service;

    @Mock
    private InMemoryDatabase database;
    private DrivingLicence validDrivingLicence ;
    private final UUID drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();

    @BeforeEach
    public void initValidDrivingLicence () {
        validDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber("123456789012345")
                .build();
    }

    @Test
    public void shouldThrowIfDrivingLicenceNotFound () {
        when(database.findById(drivingLicenceId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(drivingLicenceId, 6);
        });
    }

    @Test
    public void shouldNotRemovePointsBelowZero () {
        when(database.findById(drivingLicenceId)).thenReturn(Optional.ofNullable(validDrivingLicence));
        when(database.save(drivingLicenceId, validDrivingLicence)).thenReturn(validDrivingLicence.withAvailablePoints(0));
        final DrivingLicence updatedDrivingLicence = service.update(drivingLicenceId, 13);
        Assertions.assertEquals(0, updatedDrivingLicence.getAvailablePoints());
    }

    @Test
    public void shouldUpdateGivenNumberOfPoints () {
        when(database.findById(drivingLicenceId)).thenReturn(Optional.ofNullable(validDrivingLicence));
        when(database.save(drivingLicenceId, validDrivingLicence)).thenReturn(validDrivingLicence.withAvailablePoints(6));
        final DrivingLicence updatedDrivingLicence = service.update(drivingLicenceId, 6) ;
        validDrivingLicence = validDrivingLicence.withAvailablePoints(6) ;
        Assertions.assertEquals(validDrivingLicence.getAvailablePoints(), updatedDrivingLicence.getAvailablePoints());
    }

    @Test
    public void shouldUpdateAndReturnDrivingLicence () {
        when(database.findById(drivingLicenceId)).thenReturn(Optional.ofNullable(validDrivingLicence));
        when(database.save(drivingLicenceId, validDrivingLicence)).thenReturn(validDrivingLicence.withAvailablePoints(6));
        final DrivingLicence updatedDrivingLicence = service.update(drivingLicenceId, 6) ;
        validDrivingLicence = validDrivingLicence.withAvailablePoints(6) ;
        Assertions.assertEquals(validDrivingLicence, updatedDrivingLicence);
    }

}
