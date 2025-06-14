package com.proiect.MyApp.Service;

import com.proiect.MyApp.Model.Programare;
import com.proiect.MyApp.Util.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProgramService {
    private final Connection con;

    public ProgramService() {
        this.con = DbConfig.getConnection();
    }


    public void create(Programare programare) {
        String query = "INSERT INTO programare (pacientid, medicid, data_ora) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, programare.getPacientId());
            pstmt.setString(2, programare.getMedicId());
            pstmt.setString(3, programare.getDataOra().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Programare programare){
        String query = "DELETE FROM programare WHERE pacientid = ? AND medicid = ? AND data_ora = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, programare.getPacientId());
            pstmt.setString(2, programare.getMedicId());
            pstmt.setString(3, programare.getDataOra().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Programare find(String programareId) throws SQLException {
        String selectSql = "SELECT * FROM programare WHERE id=?";

        try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            pstmt.setString(1, programareId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Programare programare = new Programare();
                    programare.setPacientId(rs.getString("pacient_id"));
                    programare.setMedicId(rs.getString("medic_id"));
                    return programare;
                } else {
                    return null;
                }
            }
        }
    }
}
