package com.proiect.MyApp.Service;

import com.proiect.MyApp.Model.Pacient;
import com.proiect.MyApp.Util.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacientService {
    private final Connection con;

    public PacientService() {
        this.con = DbConfig.getConnection();
    }

    public void create(Pacient p) throws SQLException {
        String insertPersonSql = "INSERT INTO pacienti(nume, prenume, cnp, istoricMedical) VALUES(?, ?, ?, ?)";

        PreparedStatement pstmt = con.prepareStatement(insertPersonSql);
        pstmt.setString(1, p.getNume());
        pstmt.setString(2, p.getPrenume());
        pstmt.setString(3, p.getCnp());
        pstmt.setString(4, p.getIstoricMedical());

        pstmt.executeUpdate();
    }
}