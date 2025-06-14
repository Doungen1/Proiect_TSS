package com.proiect.MyApp.Model;

public class Reteta {
    private String pacientId;
    private String medicId;
    private String diagnostic;
    private String tratament;

    public Reteta() {
        // Constructor implicit, nu este necesară inițializarea câmpurilor la valori implicite
    }
    // Getters and setters
    public String getPacientId() {
        return pacientId;
    }

    public void setPacientId(String pacientId) {
        this.pacientId = pacientId;
    }

    public String getMedicId() {
        return medicId;
    }

    public void setMedicId(String medicId) {
        this.medicId = medicId;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTratament() {
        return tratament;
    }

    public void setTratament(String tratament) {
        this.tratament = tratament;
    }
}
