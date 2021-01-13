package com.example.demo.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ClientCoachMembershipFlx {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientFlx clientFlx;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    CoachFlx coachFlx;

    //@OneToMany()
    //Set<TrackingFlx> clientTrackings;

    LocalDateTime registeredAt;
    int membershipDurationDays;

    public ClientCoachMembershipFlx(){
    }

    public ClientCoachMembershipFlx(ClientFlx clientFlx, CoachFlx coachFlx, LocalDateTime registeredAt, int membershipDurationDays) {
        this.clientFlx = clientFlx;
        this.coachFlx = coachFlx;
        this.registeredAt = registeredAt;
        this.membershipDurationDays = membershipDurationDays;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientFlx getClientFlx() {
        return clientFlx;
    }

    public void setClientFlx(ClientFlx clientFlx) {
        this.clientFlx = clientFlx;
    }

    public CoachFlx getCoachFlx() {
        return coachFlx;
    }

    public void setCoachFlx(CoachFlx coachFlx) {
        this.coachFlx = coachFlx;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public int getMembershipDurationDays() {
        return membershipDurationDays;
    }

    public void setMembershipDurationDays(int membershipDurationDays) {
        this.membershipDurationDays = membershipDurationDays;
    }
}
