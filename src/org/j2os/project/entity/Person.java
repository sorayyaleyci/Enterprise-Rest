package org.j2os.project.entity;

import java.io.Serializable;

public class Person implements Serializable {
    private long id;
    private String name,family;
    public Person(){}
    public Person(String name,String family){
        this.name=name;
        this.family=family;

    }
    public Person(long id,String name, String family){
        this.id=id;
        this.name=name;
        this.family=family;
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }
}
