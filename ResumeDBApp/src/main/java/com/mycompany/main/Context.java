/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.dao.impl.CountryDaoImpl;
import com.mycompany.dao.impl.EmploymentHistoryDaoImpl;
import com.mycompany.dao.impl.NationalityDaoImpl;
import com.mycompany.dao.impl.SkillDaoImpl;
import com.mycompany.dao.impl.UserDaoImpl;
import com.mycompany.dao.impl.UserSkillDaoImpl;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.dao.inter.EmploymentHistoryDaoInter;
import com.mycompany.dao.inter.NationalityDaoInter;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.dao.inter.UserSkillDaoInter;

/**
 *
 * @author Ravan
 */
public class Context {

    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }

    public static SkillDaoInter instanceSkillDao() {
        return new SkillDaoImpl();
    }

    public static NationalityDaoInter instanceNationalityDao() {
        return new NationalityDaoImpl();
    }

}
