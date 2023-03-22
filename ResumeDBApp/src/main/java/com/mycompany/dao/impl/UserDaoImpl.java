/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Country;
import com.mycompany.entity.User;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.Nationality;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ravan
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        Date birthDate = rs.getDate("birthdate");
        String profileDescription = rs.getString("profile_description");
        String adress = rs.getString("adress");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthPlace");
        Nationality nationality = new Nationality(nationalityId, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthPlaceStr, nationality);
        return new User(id, name, surname, phone, email, profileDescription, adress, birthDate, nationality, birthplace);
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            String sql = "SELECT"
                    + "	u.*,"
                    + "	n.nationality,"
                    + "	c.name AS birthplace "
                    + "FROM"
                    + "	USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where 1=1 ";
            if (name != null && !name.trim().isBlank()) {
                sql += "and u.name = ? ";
            }
            if (surname != null && !surname.trim().isBlank()) {
                sql += "and u.surname = ? ";
            }
            if (nationalityId != null) {
                sql += "and u.nationality_id = ? ";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isBlank()) {
                stmt.setString(i, name);
                i++;
            }

            if (surname != null && !surname.trim().isBlank()) {
                stmt.setString(i, surname);
                i++;
            }

            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name = ?, surname = ?, phone = ?, email = ?, profile_description = ?, adress = ?, nationality_id = ?, birthplace_id = ?, birthdate =? where id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAdress());
            stmt.setInt(7, u.getNationality().getId());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setDate(9, u.getBirthDate());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("Delete from user where id = ?");
            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            System.out.println("started");
            Statement stmt = c.createStatement();
            stmt.execute("SELECT "
                    + "u.*,"
                    + "n.nationality,"
                    + "c.NAME AS birthplace "
                    + "FROM "
                    + "USER u "
                    + "LEFT JOIN country n ON u.nationality_id = n.id "
                    + "LEFT JOIN country c ON u.birthplace_id = c.id "
                    + "WHERE "
                    + "u.id = " + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email, profile_description, adress) values(?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAdress());

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

}
