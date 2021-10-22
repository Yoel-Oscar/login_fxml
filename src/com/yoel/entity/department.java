package com.yoel.entity;

/**
 * author 1672063 Yoel Oscar
 */

public class department {

    private String id;
    private String name;
    private Faculty faculty;

    public department() {
    }

    public department(String id, String name, Faculty fclty) {
        this.id = id;
        this.name = name;
        this.faculty = fclty;
    }
    public Faculty getFaculty() {
        return faculty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;

    }

    @Override
    public String toString() {
        return "department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
