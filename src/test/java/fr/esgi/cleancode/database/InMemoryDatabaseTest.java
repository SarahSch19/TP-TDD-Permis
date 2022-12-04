package fr.esgi.cleancode.database;

import fr.esgi.cleancode.model.DrivingLicence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryDatabaseTest {

    private final InMemoryDatabase database = InMemoryDatabase.getInstance();

    @Test
    void should_save_and_find_driving_licence() {
        final var drivingLicenceId = UUID.randomUUID();
        final var given = DrivingLicence.builder().id(drivingLicenceId).build();

        Assertions.assertThatNoException().isThrownBy(() -> database.save(drivingLicenceId, given));
        final var actual = database.findById(drivingLicenceId);

        assertThat(actual).containsSame(given);
    }
}