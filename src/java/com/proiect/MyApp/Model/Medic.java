package com.proiect.MyApp.Model;

public class Medic {
    private long id;
    private String nume;
    private String prenume;
    private String specializare;

    public Medic(String nume, String prenume, String specializare) {
        this.nume = nume;
        this.prenume = prenume;
        this.specializare = specializare;
    }

    public Medic() {
        this.id = 0;
        this.nume = "";
        this.prenume = "";
        this.specializare = "";
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

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public long getId() { // Și această metodă
        return id;
    }

    public void setId(long id) { // Și această metodă
        this.id = id;
    }

    @Override
    public String toString() {
        return "java.MyApp.Model.Medic {" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", specializare='" + specializare + '\'' +
                '}';
    }
}
