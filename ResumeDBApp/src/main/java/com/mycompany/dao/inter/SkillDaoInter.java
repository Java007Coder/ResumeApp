/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao.inter;

import com.mycompany.entity.Skill;
import java.util.List;

/**
 *
 * @author Ravan
 */
public interface SkillDaoInter {

    public  List<Skill> getAll();

    public Skill getById(int id);

    public boolean updateSkill(Skill u);

    public boolean removeSkill(int id);

    public List<Skill> getByName(String name);

    public boolean insertSkill(Skill skl);
}
