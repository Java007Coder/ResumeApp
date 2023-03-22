/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.NationalityDaoInter;
import com.mycompany.entity.Nationality;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ravan
 */
public class NationalityDaoImpl extends AbstractDAO implements NationalityDaoInter {

    private Nationality getNationality(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String nationality = rs.getString("nationality");
        return new Nationality(id, nationality);
    }

    @Override
    public List<Nationality> getAllNationality() {
        List<Nationality> result = new ArrayList<>();
        try ( Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT c.id, c.nationality FROM country c");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Nationality nationality = getNationality(rs);
                result.add(nationality);
            }
        } catch (Exception ex) {
        }
        return result;
    }

}
