/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.entity.Country;
import com.mycompany.entity.Nationality;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ravan
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        return new Country(id, name, new Nationality(id, nationality));
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<>();
        try ( Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from country c");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country country = getCountry(rs);
                result.add(country);
            }
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public Country getById(int userId) {
        Country country = null;
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("Select * from country where Id = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                country = getCountry(rs);
            }
        } catch (Exception ex) {
            System.err.println("No countries were selected...");
        }
        return country;
    }

    @Override
    public boolean updateCountry(Country u) {
        boolean b = false;
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("Update country Set name = ? , nationality = ? where id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality().getNationality());
            if (u.getId() == 0) {
                throw new SQLException("Id value is not defined");
            } else if (u.getNationality().getId() != u.getId()) {
                throw new SQLException("Country and Nationality id are not matching"
                        + "\nPlease check and try again");
            }
            stmt.setInt(3, u.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public int removeCountry(int id) {
        try ( Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
            int j = stmt.executeUpdate("DELETE FROM country WHERE id = " + id + ";");
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
            if (j == 0) {
                System.err.println("This country does not exist...");
            }
            return j;
        } catch (Exception ex) {
            System.out.println("Please insert the right values...");
            return -1;
        }
    }

    @Override
    public int addCountry(Country country) {
        try ( Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("insert into country(name,nationality) values(?,?)");
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getNationality().getNationality());
            return stmt.executeUpdate();
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public int addCountryById(Country country) {
        try ( Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("insert into country(id,name,nationality) values(?,?,?)");
            stmt.setInt(1, country.getId());
            stmt.setString(2, country.getName());
            stmt.setString(3, country.getNationality().getNationality());
            return stmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println("This country is already exists...");
            return -1;
        }
    }
}
