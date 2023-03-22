/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.dao.inter.UserDaoInter;

/**
 * @author Ravan
 */
public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter user = Context.instanceUserDao();
        System.out.println(user.getAll(null, null, 1));
    }
}
