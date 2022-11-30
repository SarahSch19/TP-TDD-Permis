package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceUpdateService {

    private final InMemoryDatabase database;

    public DrivingLicence update (UUID drivingLicenceId, Integer pointsTORemove) throws ResourceNotFoundException {
        var drivingLicenceToUpdate = database.findById(drivingLicenceId) ;
        if (Objects.equals(drivingLicenceId, Optional.empty())) {
            throw new ResourceNotFoundException("Driving licence with id " + drivingLicenceId.toString() + " not found");
        }

        final var updatedPoints = Math.max(drivingLicenceToUpdate.get().getAvailablePoints() - pointsTORemove, 0);
        DrivingLicence updatedDrivingLicence = drivingLicenceToUpdate.get().withAvailablePoints(updatedPoints);
        return database.save(drivingLicenceId, updatedDrivingLicence);
    }
}
