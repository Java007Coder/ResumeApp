/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao.inter;

import com.mycompany.entity.User_Skill;
import java.util.List;

/**
 *
 * @author Ravan
 */
public interface UserSkillDaoInter {

    public List<User_Skill> getAllSkillByUserId(int id);

    public int insertUserSkill(User_Skill u);

    public int updateUserSkill(User_Skill u);

    public int removeUserSkill(int id);
}
