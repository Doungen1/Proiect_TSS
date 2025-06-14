package com.proiect.MyApp.Model;

import java.time.LocalDateTime;

public class Programare {
    private String pacientId;
    private String medicId;
    private LocalDateTime dataOra;

    // Getteri și setteri
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

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(String dataOra) {
        this.dataOra = LocalDateTime.parse(dataOra);
    }

    // Rescrierea metodei equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Programare that = (Programare) obj;
        return pacientId.equals(that.pacientId) &&
                medicId.equals(that.medicId) &&
                dataOra.equals(that.dataOra);
    }

    // Rescrierea metodei hashCode()
    @Override
    public int hashCode() {
        return java.util.Objects.hash(pacientId, medicId, dataOra);
    }

    // Rescrierea metodei toString()
    @Override
    public String toString() {
        return "java.MyApp.Model.Programare{" +
                "pacientId=" + pacientId +
                ", medicId=" + medicId +
                ", dataOra=" + dataOra +
                '}';
    }
}
