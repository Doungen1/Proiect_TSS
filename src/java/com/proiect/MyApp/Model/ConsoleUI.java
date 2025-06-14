package com.proiect.MyApp.Model;

import java.util.Scanner;

public class ConsoleUI {
    private final com.proiect.MyApp.Model.CabinetMedical cabinetMedicalService;
    private final Scanner scanner;

    public ConsoleUI(com.proiect.MyApp.Model.CabinetMedical cabinetMedicalService) {
        this.cabinetMedicalService = cabinetMedicalService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean quit = false;
        while (!quit) {
            // print the menu
            System.out.println("\nMeniu Principal:");
            System.out.println("1. Adaugă medic");
            System.out.println("2. Adaugă pacient");
            System.out.println("3. Creează o programare");
            System.out.println("4. Anulează o programare");
            System.out.println("5. Emite o rețetă");
            System.out.println("0. Ieșire");
            System.out.print("Alege o opțiune: ");

            String line = scanner.nextLine();
            int option;
            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Opțiune invalidă.");
                continue;      // back to printing the menu
            }

            try {
                switch (option) {
                    case 1:  adaugaMedic();        break;
                    case 2:  adaugaPacient();      break;
                    case 3:  adaugaProgramare();   break;
                    case 4:  anuleazaProgramare(); break;
                    case 5:  emiteReteta();        break;
                    case 0:  quit = true;          break;
                    default: System.out.println("Opțiune invalidă.");
                }
            } catch (Exception e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }
    }


    private void adaugaMedic() throws Exception {
        System.out.println("Introduceți numele medicului:");
        String nume = scanner.nextLine();
        System.out.println("Introduceți prenumele medicului:");
        String prenume = scanner.nextLine();
        System.out.println("Introduceți specializarea medicului:");
        String specializare = scanner.nextLine();

        cabinetMedicalService.adaugaMedic(nume, prenume, specializare);
    }

    private void adaugaPacient() throws Exception {
        System.out.println("Introduceți numele pacientului:");
        String nume = scanner.nextLine();
        System.out.println("Introduceți prenumele pacientului:");
        String prenume = scanner.nextLine();
        System.out.println("Introduceți CNP-ul pacientului:");
        String cnp = scanner.nextLine();
        System.out.println("Introduceți istoricul medical al pacientului (dacă este disponibil):");
        String istoricMedical = scanner.nextLine();

        cabinetMedicalService.adaugaPacient(nume, prenume, cnp, istoricMedical);
    }

    private void adaugaProgramare() throws Exception {
        System.out.println("Introduceți numele pacientului pentru programare:");
        String numePacient = scanner.nextLine();
        System.out.println("Introduceți numele medicului pentru programare:");
        String numeMedic = scanner.nextLine();
        System.out.println("Introduceți data și ora programării (yyyy-MM-ddTHH:mm):");
        String dataOraString = scanner.nextLine();

        cabinetMedicalService.adaugaProgramare(numePacient, numeMedic, dataOraString);
    }

    private void anuleazaProgramare() throws Exception {
        System.out.println("Introduceți ID-ul programării pentru anulare:");
        String programareId = scanner.nextLine();

        cabinetMedicalService.anuleazaProgramare(programareId);
    }

    private void emiteReteta() throws Exception {
        System.out.println("Introduceți ID-ul pacientului pentru care se emite rețeta:");
        String pacientId = scanner.nextLine();
        System.out.println("Introduceți ID-ul medicului care emite rețeta:");
        String medicId = scanner.nextLine();
        System.out.println("Introduceți diagnosticul:");
        String diagnostic = scanner.nextLine();
        System.out.println("Introduceți tratamentul:");
        String tratament = scanner.nextLine();

        cabinetMedicalService.emiteReteta(pacientId, medicId, diagnostic, tratament);
    }
}

