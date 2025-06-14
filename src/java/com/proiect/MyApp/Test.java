package com.proiect.MyApp;

import com.proiect.MyApp.Model.Medic;
import com.proiect.MyApp.Service.MedicService;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    private static final Scanner scanner = new Scanner(System.in);
    private static MedicService medicService;

    public static void main(String[] args) {
        medicService = new MedicService();
        boolean quit = false;
        while (!quit) {
            System.out.println("1. Adauga medic");
            System.out.println("2. Afiseaza medic");
            System.out.println("3. Actualizeaza medic");
            System.out.println("4. Sterge medic");
            System.out.println("5. Iesire");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid Input! Please enter a number.");
                scanner.nextLine();
                continue;
            }
            int option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 1:
                        createMedic();
                        break;
                    case 2:
                        readMedic();
                        break;
                    case 3:
                        updateMedic();
                        break;
                    case 4:
                        deleteMedic();
                        break;
                    case 5:
                        closeService();
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number from 1 to 5.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("A occurat o eroare cu operatiunea de baza de date: " + e.getMessage());
            }
        }
    }


    private static void createMedic() throws SQLException {
        Medic medic = new Medic();
        System.out.println("Introduceți numele medicului:");
        medic.setNume(scanner.nextLine());
        System.out.println("Introduceți prenumele medicului:");
        medic.setPrenume(scanner.nextLine());
        System.out.println("Introduceți specializarea medicului:");
        medic.setSpecializare(scanner.nextLine());
        medicService.create(medic);
    }

    private static void readMedic() throws SQLException {
        System.out.println("Introduceți ID-ul medicului:");
        long id = scanner.nextLong();
        scanner.nextLine();
        Medic medicCautat = medicService.read(id);
        System.out.println("Detalii medic: " + medicCautat);
    }

    private static void updateMedic() throws SQLException {
        System.out.println("Introduceți ID-ul medicului pentru actualizare:");
        long idUpdate = scanner.nextLong();
        scanner.nextLine();
        Medic medicUpdate = medicService.read(idUpdate);
        System.out.println("Introduceți noua specializare a medicului:");
        medicUpdate.setSpecializare(scanner.nextLine());
        medicService.update(medicUpdate);
    }

    private static void deleteMedic() throws SQLException {
        System.out.println("Introduceți ID-ul medicului pentru ștergere:");
        long idDelete = scanner.nextLong();
        scanner.nextLine();
        medicService.delete(idDelete);
    }

    private static void closeService() {
        try {
            medicService.closeConnection();
        } catch (SQLException e) {
            System.out.println("A aparut o problema la inchiderea conexiunii. Detalii: " + e.getMessage());
        }
    }
}