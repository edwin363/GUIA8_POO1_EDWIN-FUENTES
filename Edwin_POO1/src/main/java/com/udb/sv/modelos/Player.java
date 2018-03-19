/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udb.sv.modelos;

/**
 *
 * @author moises
 */
public class Player {

    private String name;
    private int age;
    private double height;
    private double weight;
    private int codigo;
    private Team team;

    public Team getCodigoEquipo() {
        return team;
    }

    public void setCodigoEquipo(Team codigoEquipo) {
        this.team = codigoEquipo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
