package com.example.demo.entity;

import com.example.demo.enums.Gender;
import com.example.demo.enums.LifeStyle;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


@Entity
@Table (name = "ClientFlx")
public class ClientFlx {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private int height;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "diet")
    private String diet;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "lifestyle")
    private LifeStyle lifeStyle;

    /*@OneToMany(mappedBy = "Client", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Documents> documentsSet  = new HashSet<>();

    @OneToMany(mappedBy = "Client", cascade = CascadeType.PERSIST)
    private Set<Tracking> trackings = new HashSet<>();*/

    //@ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "coachId")
    private CoachFlx coachFlx;

    public ClientFlx(@NotEmpty String firstName, @NotEmpty String lastName, double weight, int height, String email, String phoneNumber, String diet, Date registrationDate, Gender gender, LifeStyle lifeStyle, CoachFlx coachFlx) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.diet = diet;
        this.registrationDate = registrationDate;
        this.gender = gender;
        this.lifeStyle = lifeStyle;
        this.coachFlx = coachFlx;
    }

    public ClientFlx(){

    }

    public CoachFlx getCoachFlx() {
        return coachFlx;
    }

    public void setCoachFlx(CoachFlx coachFlx) {
        this.coachFlx = coachFlx;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

}
