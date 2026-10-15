package services;

import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SalleServiceTest {

    private SalleService service;

    @BeforeEach
    public void setUp() {
        service = new SalleService();
    }

    @Test
    public void testCreateSalle() {
        Salle salle = new Salle();
        salle.setCode("S2");
        salle.setLibelle("Salle Réseau");

        boolean result = service.create(salle);
        Assertions.assertTrue(result, "La création de la salle doit réussir");
        Assertions.assertTrue(salle.getId() > 0, "L'id généré doit être supérieur à 0");
    }

    @Test
    public void testGetById() {
        Salle salle = new Salle("S3", "Salle Multimédia");
        service.create(salle);

        Salle found = service.getById(salle.getId());
        Assertions.assertNotNull(found, "La salle doit être trouvée par son id");
        Assertions.assertEquals("S3", found.getCode());
        Assertions.assertEquals("Salle Multimédia", found.getLibelle());
    }

    @Test
    public void testUpdateSalle() {
        Salle salle = new Salle("S4", "Salle Initial");
        service.create(salle);

        salle.setLibelle("Salle Modifiée");
        boolean updated = service.update(salle);
        Assertions.assertTrue(updated, "La mise à jour doit réussir");

        Salle found = service.getById(salle.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals("Salle Modifiée", found.getLibelle());
    }

    @Test
    public void testGetAll() {
        Salle salle = new Salle("S5", "Salle Test List");
        service.create(salle);

        List<Salle> list = service.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty(), "La liste des salles ne doit pas être vide");
    }

    @Test
    public void testDeleteSalle() {
        Salle salle = new Salle("S6", "Salle à supprimer");
        service.create(salle);
        int id = salle.getId();

        boolean deleted = service.delete(salle);
        Assertions.assertTrue(deleted, "La suppression doit réussir");

        Salle found = service.getById(id);
        Assertions.assertNull(found, "La salle supprimée ne doit plus exister");
    }
}
