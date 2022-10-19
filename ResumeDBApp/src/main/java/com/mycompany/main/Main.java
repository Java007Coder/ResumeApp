/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.dao.inter.SkillDaoInter;

/**
 *
 * @author Ravan
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SkillDaoInter skill = Context.instanceSkillDao();
        System.out.println(skill.getAllSkill());
    }

}
