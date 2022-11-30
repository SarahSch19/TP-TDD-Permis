package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;
    private DrivingLicence validDrivingLicence ;
    private final UUID drivingLicenceId = DrivingLicenceIdGenerationService.generateNewDrivingLicenceId();

    @Test
    void shouldFindDrivingLicenceInDatabase() {
        final var validSocialSecurityNumber = "123456789012345" ;
        validDrivingLicence = DrivingLicence.builder()
                .id(drivingLicenceId)
                .driverSocialSecurityNumber(validSocialSecurityNumber)
                .build();
        when(database.findById(drivingLicenceId)).thenReturn(Optional.ofNullable(validDrivingLicence));

        final var actual = service.findById(drivingLicenceId);

        Assertions.assertSame(Optional.ofNullable(validDrivingLicence), actual);
        verify(database).findById(drivingLicenceId);
        verifyNoMoreInteractions(database);
    }

    @Test
    void shouldNotFindDrivingLicenceInDatabase() {
        when(database.findById(drivingLicenceId)).thenReturn(Optional.empty());

        final var actual = service.findById(drivingLicenceId);

        Assertions.assertNull(actual);
        verify(database).findById(drivingLicenceId);
        verifyNoMoreInteractions(database);
    }
}