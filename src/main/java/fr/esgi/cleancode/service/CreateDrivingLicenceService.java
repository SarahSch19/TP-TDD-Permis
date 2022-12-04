package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import fr.esgi.cleancode.utils.SocialSecurityNumberValidator;

public class CreateDrivingLicenceService {

    private final InMemoryDatabase database = InMemoryDatabase.getInstance();

    public DrivingLicence save(DrivingLicence drivingLicence) throws InvalidDriverSocialSecurityNumberException {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(
                drivingLicence.getDriverSocialSecurityNumber()
        );
        if(!socialSecurityNumberIsValid)
            throw new InvalidDriverSocialSecurityNumberException("Invalid social security number");

        return database.save(drivingLicence.getId(), drivingLicence);
    }
}
