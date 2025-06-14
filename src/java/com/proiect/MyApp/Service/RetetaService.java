package com.proiect.MyApp.Service;

import com.proiect.MyApp.Model.Reteta;
import com.proiect.MyApp.Util.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RetetaService {
    private final Connection con;

    public RetetaService(){
        this.con = DbConfig.getConnection();
    }

    public void create(Reteta reteta){
        String query = "INSERT INTO reteta (pacientid, medicid, diagnostic, tratament) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, reteta.getPacientId());
            pstmt.setString(2, reteta.getMedicId());
            pstmt.setString(3, reteta.getDiagnostic());
            pstmt.setString(4, reteta.getTratament());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
