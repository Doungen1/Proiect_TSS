package com.proiect.MyApp.Model;

public class Pacient {
    private String nume;
    private String prenume;
    private String cnp;
    private String istoricMedical;

    public Pacient() {
        this.nume = "";
        this.prenume = "";
        this.cnp = "";
        this.istoricMedical = "";
    }

    // Getteri și setteri
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        if (cnp == null || cnp.length() != 13) {
            throw new IllegalArgumentException("CNP-ul trebuie să aibă exact 13 caractere!");
        }
        this.cnp = cnp;
    }

    public String getIstoricMedical() {
        return istoricMedical;
    }

    public void setIstoricMedical(String istoricMedical) {
        this.istoricMedical = istoricMedical;
    }
}
