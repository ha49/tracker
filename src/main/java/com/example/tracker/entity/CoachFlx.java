package com.example.tracker.entity;

import com.example.tracker.auth.UserFlx;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

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
    private String specialization;

    @Column(name = "phone_number")
    private String phoneNumber;


    //@OneToMany(mappedBy = "coach_flx")
    //@OneToMany(mappedBy = "coachFlx", cascade = CascadeType.ALL)
    @JsonIgnore
    @OneToMany(mappedBy = "coachFlx", orphanRemoval = true)
    //@JsonBackReference(value = "membership")
            Set<ClientCoachMembershipFlx> memberships;

    //@OneToMany(mappedBy = "coachFlx", cascade = CascadeType.ALL)
    @JsonIgnore
    @OneToMany(mappedBy = "coachFlx", orphanRemoval = true)
    //@JsonBackReference(value = "links")
            Set<LinkFlx> links;

    @OneToOne
    private UserFlx userFlx;

    /*@OneToMany(mappedBy = "Coach", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Links> linksSet  = new HashSet<>();*/

    public CoachFlx(String firstName, String lastName, String specialization, String phoneNumber, UserFlx userFlx) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;

        this.phoneNumber = phoneNumber;
        this.userFlx = userFlx;

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
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

    public Set<ClientCoachMembershipFlx> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<ClientCoachMembershipFlx> memberships) {
        this.memberships = memberships;
    }
}
