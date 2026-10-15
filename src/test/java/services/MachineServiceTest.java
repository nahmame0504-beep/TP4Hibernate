package services;

import entities.Machine;
import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MachineServiceTest {

    private MachineService machineService;
    private SalleService salleService;

    @BeforeEach
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();
    }

    @Test
    public void testCreateAndGetMachine() {
        Salle salle = new Salle("SM1", "Salle Machines");
        salleService.create(salle);

        Machine machine = new Machine("M-201", "HP", salle);
        boolean created = machineService.create(machine);

        Assertions.assertTrue(created, "La création de la machine doit réussir");
        Assertions.assertTrue(machine.getId() > 0, "L'id de la machine doit être généré");

        Machine found = machineService.getById(machine.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals("M-201", found.getRef());
        Assertions.assertEquals("HP", found.getMarque());
        Assertions.assertNotNull(found.getSalle());
        Assertions.assertEquals("SM1", found.getSalle().getCode());
    }

    @Test
    public void testFindByMarque() {
        Salle salle = new Salle("SM2", "Salle Lenovo");
        salleService.create(salle);

        Machine m1 = new Machine("LEN-01", "Lenovo", salle);
        Machine m2 = new Machine("LEN-02", "Lenovo", salle);
        machineService.create(m1);
        machineService.create(m2);

        List<Machine> lenovoMachines = machineService.findByMarque("Lenovo");
        Assertions.assertNotNull(lenovoMachines);
        Assertions.assertTrue(lenovoMachines.size() >= 2, "Au moins 2 machines Lenovo doivent être trouvées");
    }

    @Test
    public void testUpdateMachine() {
        Salle salle = new Salle("SM3", "Salle Update");
        salleService.create(salle);

        Machine machine = new Machine("M-301", "Asus", salle);
        machineService.create(machine);

        machine.setMarque("Asus ROG");
        boolean updated = machineService.update(machine);
        Assertions.assertTrue(updated);

        Machine found = machineService.getById(machine.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals("Asus ROG", found.getMarque());
    }

    @Test
    public void testDeleteMachine() {
        Salle salle = new Salle("SM4", "Salle Delete");
        salleService.create(salle);

        Machine machine = new Machine("M-401", "Acer", salle);
        machineService.create(machine);
        int id = machine.getId();

        boolean deleted = machineService.delete(machine);
        Assertions.assertTrue(deleted);

        Machine found = machineService.getById(id);
        Assertions.assertNull(found);
    }
}
