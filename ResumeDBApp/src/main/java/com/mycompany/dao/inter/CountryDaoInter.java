/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao.inter;

import com.mycompany.entity.Country;
import java.util.List;

/**
 *
 * @author Ravan
 */
public interface CountryDaoInter {

    public List<Country> getAllCountry();

    public Country getById(int userId);

    public boolean updateCountry(Country u);

    public int removeCountry(int id);

    public int addCountry(Country country);

    public int addCountryById(Country country);
}
