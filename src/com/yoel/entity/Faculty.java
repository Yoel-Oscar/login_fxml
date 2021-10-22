package com.yoel.entity;

/**
 * author 1672063 Yoel Oscar
 */

public class Faculty {

    private int idUSer;
    private String nameUser;

    public int getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(int idUSer) {
        this.idUSer = idUSer;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @Override
    public String toString() {
        return nameUser ;
    }
}
