package com.ernstblignaut.pinkroccade.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date birthDate;

    // I have removed the nullable = false from the parent1 and parent2 because then I would not
    // be able to insert initial values because of foreign key constraint errors
    // A SOLUTION could be to first add values before adding the nullable = false constraint. (Lack of time - 2hours)
    @ManyToOne
    @JoinColumn
    private Person parent1;

    @ManyToOne
    @JoinColumn
    private Person parent2;

    @ManyToMany
    @JoinColumn
    private List<Person> children;

    @ManyToOne
    @JoinColumn
    private Person partner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Person getParent1() {
        return parent1;
    }

    public void setParent1(Person parent1) {
        this.parent1 = parent1;
    }

    public Person getParent2() {
        return parent2;
    }

    public void setParent2(Person parent2) {
        this.parent2 = parent2;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }
}
