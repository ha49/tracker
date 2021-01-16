package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class ComboContentFlx {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

    public ComboContentFlx(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ComboContentFlx() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
