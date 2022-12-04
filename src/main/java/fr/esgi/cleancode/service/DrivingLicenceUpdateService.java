package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceUpdateService {

    private final InMemoryDatabase database;

    public DrivingLicence updatePoints(UUID drivingLicenceId, Integer pointsTORemove) throws ResourceNotFoundException {
        var drivingLicenceToUpdate = this.database.findById(drivingLicenceId) ;
        if (drivingLicenceToUpdate.isEmpty()) {
            throw new ResourceNotFoundException("Driving licence with id " + drivingLicenceId.toString() + " not found");
        }
        DrivingLicence updatedDrivingLicence = drivingLicenceToUpdate.get();

        final var updatedPoints = Math.max(updatedDrivingLicence.getAvailablePoints() - pointsTORemove, 0);
        updatedDrivingLicence = updatedDrivingLicence.withAvailablePoints(updatedPoints);
        return database.save(drivingLicenceId, updatedDrivingLicence);
    }
}
