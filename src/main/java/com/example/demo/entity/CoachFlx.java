package com.example.demo.entity;

import com.example.demo.auth.UserFlx;
import com.example.demo.enums.Specialization;

import javax.persistence.*;

@Entity
public class CoachFlx {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "specialization")
    private Specialization specialization;
//    @Column(name = "email")
//    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    private UserFlx userFlx;

    /*@OneToMany(mappedBy = "Coach", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Links> linksSet  = new HashSet<>();*/

    public CoachFlx( String firstName, String lastName, Specialization specialization,  String phoneNumber, UserFlx userFlx) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;

        this.phoneNumber = phoneNumber;
        this.userFlx=userFlx;

    }

    public CoachFlx() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }




    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserFlx getUserFlx() {
        return userFlx;
    }

    public void setUserFlx(UserFlx userFlx) {
        this.userFlx = userFlx;
    }
}
