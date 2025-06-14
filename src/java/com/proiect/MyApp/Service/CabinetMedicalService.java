package com.proiect.MyApp.Service;

import com.proiect.MyApp.Model.Medic;
import com.proiect.MyApp.Model.Pacient;
import com.proiect.MyApp.Model.Programare;
import com.proiect.MyApp.Model.Reteta;

import java.sql.SQLException;

public class CabinetMedicalService {
    private final MedicService medicService;
    private final com.proiect.MyApp.Service.PacientService pacientService;
    private final ProgramService programService;
    private final RetetaService retetaService;

    public CabinetMedicalService(MedicService medicService,
                                 com.proiect.MyApp.Service.PacientService pacientService,
                                 ProgramService programService,
                                 RetetaService retetaService) {
        this.medicService = medicService;
        this.pacientService = pacientService;
        this.programService = programService;
        this.retetaService = retetaService;
    }

    public void adaugaMedic(String nume, String prenume, String specializare) throws SQLException {
        Medic medic = new Medic();
        medic.setNume(nume);
        medic.setPrenume(prenume);
        medic.setSpecializare(specializare);

        medicService.create(medic);
        System.out.println("Medicul a fost adăugat cu succes.");
    }

    public void adaugaPacient(String nume, String prenume, String cnp, String istoricMedical) throws SQLException {
        Pacient pacient = new Pacient();
        pacient.setNume(nume);
        pacient.setPrenume(prenume);
        pacient.setCnp(cnp);
        pacient.setIstoricMedical(istoricMedical);

        pacientService.create(pacient);
        System.out.println("Pacientul a fost adăugat cu succes.");
    }

    public void adaugaProgramare(String pacientId, String medicId, String dataOra) throws SQLException {
        Programare programare = new Programare();
        programare.setPacientId(pacientId);
        programare.setMedicId(medicId);
        programare.setDataOra(dataOra);

        programService.create(programare);
        System.out.println("Programarea a fost adăugată cu succes");
    }

    public void anuleazaProgramare(String programareId) throws SQLException {
        Programare programare = programService.find(programareId);
        if (programare != null) {
            programService.delete(programare);
            System.out.println("Programarea a fost anulată cu succes.");
        } else {
            System.out.println("Programarea nu a fost găsită.");
        }
    }

    public void emiteReteta(String pacientId, String medicId, String diagnostic, String tratament) throws SQLException {
        Reteta reteta = new Reteta();
        reteta.setPacientId(pacientId);
        reteta.setMedicId(medicId);
        reteta.setDiagnostic(diagnostic);
        reteta.setTratament(tratament);

        retetaService.create(reteta);
        System.out.println("Reţeta a fost emisă cu succes.");
    }
}