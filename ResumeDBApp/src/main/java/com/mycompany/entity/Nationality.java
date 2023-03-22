/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.util.Objects;

/**
 *
 * @author Ravan
 */
public class Nationality {

    private int id;
    private String nationality;

    public Nationality() {
    }

    public Nationality(int id, String nationality) {
        this.id = id;
        this.nationality = nationality;
    }

    public Nationality(String nationality) {
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nationality other = (Nationality) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nationality, other.nationality);
    }

    @Override
    public String toString() {
        return nationality;
    }

}
