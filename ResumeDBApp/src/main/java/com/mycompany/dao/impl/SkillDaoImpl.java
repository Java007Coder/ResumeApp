/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ravan
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));

            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return list;
    }

    @Override
    public Skill getById(int userId) {
        Skill usr = null;
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM skill WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                usr = new Skill(id, name);
            }
        } catch (Exception ex) {
        }
        return usr;
    }

    @Override
    public boolean updateSkill(Skill u) {

        boolean b = true;
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("UPDATE skill SET name=? WHERE id= ?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            b = stmt.execute();
        } catch (Exception ex) {
            b = false;
        }
        return b;
    }

    public boolean insertSkill(Skill skl) {
        boolean b = true;
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert skill (name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getName());
            b = stmt.execute();
            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                skl.setId(genKeys.getInt(1));
            }
        } catch (Exception ex) {
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeSkill(int id) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM skill WHERE id=?;");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Skill> getByName(String sname) {
        List<Skill> list = new ArrayList<>();
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM skill WHERE name LIKE ?;");
            stmt.setString(1, sname);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            System.err.println("Something went wrong...");
        }
        return list;
    }

}
