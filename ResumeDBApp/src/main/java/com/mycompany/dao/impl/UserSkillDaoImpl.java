/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.mycompany.entity.Skill;
import com.mycompany.entity.User;
import com.mycompany.entity.User_Skill;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ravan
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    public User_Skill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("user_skill_id");
        int userId = rs.getInt("id");
        int skill_id = rs.getInt("skill_id");
        String skill_name = rs.getString("skill_name");
        int power = rs.getInt("power");
        User_Skill us = new User_Skill(userSkillId, new User(userId), new Skill(skill_id, skill_name), power);
        return us;
    }

    @Override
    public List<User_Skill> getAllSkillByUserId(int id) {
        List<User_Skill> list = new ArrayList<>();
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + " us.id as user_skill_id,"
                    + " u.*,"
                    + " us.skill_id,s.name AS Skill_name ,"
                    + " us.power "
                    + " FROM "
                    + " user_skill us "
                    + " LEFT JOIN user u ON us.user_id=u.id "
                    + " LEFT JOIN skill s ON us.skill_id=s.id "
                    + " WHERE us.user_id = ? ");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User_Skill us = getUserSkill(rs);
                list.add(us);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
        }
        return list;
    }

    public int insertUserSkill(User_Skill u) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO user_skill (skill_id , user_id ,power) VALUES (? , ? ,  ? ) ;");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());
            return stmt.executeUpdate();
        } catch (Exception ex) {
            return -1;
        }
    }

    public int updateUserSkill(User_Skill u) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("UPDATE user_skill SET skill_id = ? , user_id =? ,power =?  WHERE id = ? ;");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());
            stmt.setInt(4, u.getId());

            return stmt.executeUpdate();
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public int removeUserSkill(int id) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM user_skill WHERE ID=?");
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (Exception ex) {
            return -1;
        }
    }
}
