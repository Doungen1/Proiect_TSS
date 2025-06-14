package com.proiect.MyApp.Model;

import com.proiect.MyApp.Service.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class CabinetMedical extends CabinetMedicalService {
    private static MedicService medicService;
    private static PacientService pacientService;
    private static ProgramService programService;
    private static RetetaService retetaService;

    static {
        try {
            medicService   = new MedicService();
            pacientService = new PacientService();
            programService = new ProgramService();
            retetaService  = new RetetaService();
        } catch (Exception e) {
            throw new RuntimeException("Nu am putut inițializa serviciile", e);
        }
    }

    private final List<Medic> medici = new ArrayList<>();
    private final TreeSet<Programare> programari = new TreeSet<>(Comparator.comparing(Programare::getDataOra));


    public CabinetMedical() {
        super(medicService, pacientService, programService, retetaService);
    }

    public void adaugaMedic(Medic medic) {
        medici.add(medic);
    }

    public void adaugaProgramare(Programare programare) {
        programari.add(programare);
    }

    public void anuleazaProgramare(Programare programare) {
        programari.remove(programare);
    }

    public List<Programare> getProgramari() {
        return new ArrayList<>(programari);
    }


    public Medic cautaMedic(String nume) {
        return medici.stream()
                .filter(m -> m.getNume().equalsIgnoreCase(nume))
                .findFirst()
                .orElse(null);
    }

}