package com.example.tracker.entity;

import com.example.tracker.auth.UserFlx;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "ClientFlx")
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

    //    @Column(name = "email")
    //    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "diet")
    private String diet;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "client_photo_link")
    private String clientPhotoLink;

    @Column(name = "lifestyle")
    private String lifeStyle;

    @Column(name = "status")
    private String status;
    //    private LifeStyle lifeStyle;

    //@OneToMany(mappedBy = "clientFlx", cascade = CascadeType.ALL)
    //@JsonBackReference(value = "membership")
    @JsonIgnore
    @OneToMany(mappedBy = "clientFlx", orphanRemoval = true)
    Set<ClientCoachMembershipFlx> memberships;

    //@OneToMany(mappedBy = "clientFlx", cascade = CascadeType.ALL)
    //@JsonBackReference(value = "documents")
    @JsonIgnore
    @OneToMany(mappedBy = "clientFlx", orphanRemoval = true)
    Set<DocumentFlx> documents;

    /*@OneToMany(mappedBy = "Client", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Documents> documentsSet  = new HashSet<>();

    @OneToMany(mappedBy = "Client", cascade = CascadeType.PERSIST)
    private Set<Tracking> trackings = new HashSet<>();*/

    //@ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "coachId")
    private CoachFlx coachFlx;

    @OneToOne
    private UserFlx userFlx;

    public ClientFlx(String firstName, String lastName, double weight, int height,
                     String phoneNumber, String diet, Date registrationDate, String gender, String clientPhotoLink, String lifeStyle,
                     String status, UserFlx userFlx) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;

        this.phoneNumber = phoneNumber;
        this.diet = diet;
        this.registrationDate = registrationDate;
        this.gender = gender;
        this.clientPhotoLink = clientPhotoLink;
        this.lifeStyle = lifeStyle;
        this.status = status;
        this.userFlx = userFlx;
    }

    public ClientFlx() {

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

    public String getClientPhotoLink() {
        return clientPhotoLink;
    }

    public void setClientPhotoLink(String clientPhotoLink) {
        this.clientPhotoLink = clientPhotoLink;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public UserFlx getUserFlx() {
        return userFlx;
    }

    public void setUserFlx(UserFlx userFlx) {
        this.userFlx = userFlx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ClientCoachMembershipFlx> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<ClientCoachMembershipFlx> memberships) {
        this.memberships = memberships;
    }

    public Set<DocumentFlx> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentFlx> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return " {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email=" + userFlx.getEmail() +
                ", registrationDate=" + registrationDate +
                ", gender='" + gender + '\'' +

                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ClientFlx)) return false;
//        ClientFlx clientFlx = (ClientFlx) o;
//        return id == clientFlx.id;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       ClientFlx that = (ClientFlx) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userFlx.getUsername(), that.userFlx.getUsername()) &&
                Objects.equals(clientPhotoLink, that.clientPhotoLink);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
