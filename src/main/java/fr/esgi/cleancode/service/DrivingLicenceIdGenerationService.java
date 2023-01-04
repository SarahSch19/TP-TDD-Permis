package fr.esgi.cleancode.service;

import java.util.UUID;

public final class DrivingLicenceIdGenerationService {

    public static UUID generateNewDrivingLicenceId() {
        return UUID.randomUUID();
    }
}
