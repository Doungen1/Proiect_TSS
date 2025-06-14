package com.proiect.MyApp.Service;

import com.proiect.MyApp.Model.Medic;
import com.proiect.MyApp.Util.DbConfig;

import java.sql.*;
import java.util.NoSuchElementException;

public class MedicService {

        private final Connection con;

        public MedicService(){
        this.con = DbConfig.getConnection();
    }
    public void create(Medic m) throws SQLException {
        String insertPersonSql = "INSERT INTO medici(nume, prenume, specializare) VALUES(?, ?, ?)";

        PreparedStatement pstmt = con.prepareStatement(insertPersonSql);
        pstmt.setString(1, m.getNume());
        pstmt.setString(2, m.getPrenume());
        pstmt.setString(3, m.getSpecializare());

        pstmt.executeUpdate();
    }
    public Medic read(long id) throws SQLException {
        String selectSql = "SELECT * FROM medici WHERE id=?";

        PreparedStatement pstmt = con.prepareStatement(selectSql);
        pstmt.setLong(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Medic m = new Medic();
            m.setId(rs.getLong("id"));
            m.setNume(rs.getString("nume"));
            m.setPrenume(rs.getString("prenume"));
            m.setSpecializare(rs.getString("specializare"));
            return m;
        } else {
            throw new NoSuchElementException("No medic with id " + id);
        }
    }

    public void update(Medic m) throws SQLException {
        String updatePersonSql = "UPDATE medici SET nume=?, prenume=?, specializare=? WHERE id=?";

        PreparedStatement pstmt = con.prepareStatement(updatePersonSql);
        pstmt.setString(1, m.getNume());
        pstmt.setString(2, m.getPrenume());
        pstmt.setString(3, m.getSpecializare());
        pstmt.setLong(4, m.getId());

        pstmt.executeUpdate();
    }

    public void delete(long id) throws SQLException {
        String deletePersonSql = "DELETE FROM medici WHERE id=?";

        PreparedStatement pstmt = con.prepareStatement(deletePersonSql);
        pstmt.setLong(1, id);

        pstmt.executeUpdate();
    }

    public void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}