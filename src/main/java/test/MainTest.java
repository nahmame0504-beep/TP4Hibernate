package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;
import util.HibernateUtil;

public class MainTest {
    public static void main(String[] args) {
        try {
            SalleService salleService = new SalleService();
            MachineService machineService = new MachineService();

            Salle salleA = new Salle();
            salleA.setCode("S1");
            salleA.setLibelle("Salle Informatique");

            salleService.create(salleA);

            Machine machine = new Machine();
            machine.setRef("M-101");
            machine.setMarque("Dell");
            machine.setSalle(salleA);

            machineService.create(machine);

            System.out.println("Données insérées avec succès !");

            // Test de la méthode HQL findByMarque (Étape 10)
            System.out.println("Recherche des machines de marque Dell :");
            for (Machine m : machineService.findByMarque("Dell")) {
                System.out.println(" - " + m.getRef() + " (" + m.getMarque() + ") dans la salle " + m.getSalle().getCode());
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
