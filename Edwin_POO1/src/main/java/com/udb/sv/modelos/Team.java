package com.udb.sv.modelos;

public class Team {

    private String name;
    private String des;
    private int codigo;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return this.name;
    }
    

}
