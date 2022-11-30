package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import fr.esgi.cleancode.utils.SocialSecurityNumberValidator;

public class CreateDrivingLicenceService {

    private final InMemoryDatabase db = InMemoryDatabase.getInstance();

    private static CreateDrivingLicenceService INSTANCE;

    private CreateDrivingLicenceService(){}

    public synchronized static CreateDrivingLicenceService getInstance() {
        if (INSTANCE == null) {
            return new CreateDrivingLicenceService();
        }
        return INSTANCE;
    }

    public DrivingLicence save(DrivingLicence drivingLicence) throws InvalidDriverSocialSecurityNumberException {
        final var socialSecurityNumberIsValid = SocialSecurityNumberValidator.validateSocialSecurityNumber(
                drivingLicence.getDriverSocialSecurityNumber()
        );
        if(!socialSecurityNumberIsValid)
            throw new InvalidDriverSocialSecurityNumberException("Invalid social security number");

        return db.save(drivingLicence.getId(), drivingLicence);
    }
}
