package com.example.tracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TrackingFlx {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn
    private ClientCoachMembershipFlx clientCoachMembershipFlx;

    @Column(name = "trackingDate")
    private Date trackingDate;
    private int dietRate;
    private int exerciseRate;
    private int mode;
    private String clientNote;
    private String coachNote;
    private boolean motivationalMessageFlag;
    private String motivationalMessagePath;

    public TrackingFlx() {

    }

    public TrackingFlx(ClientCoachMembershipFlx clientCoachMembershipFlx, Date trackingDate, int dietRate,
                       int exerciseRate, int mode, String clientNote,
                       String coachNote, boolean motivationalMessageFlag, String motivationalMessagePath) {
        this.trackingDate = trackingDate;
        this.dietRate = dietRate;
        this.exerciseRate = exerciseRate;
        this.mode = mode;
        this.clientNote = clientNote;
        this.coachNote = coachNote;
        this.motivationalMessageFlag = motivationalMessageFlag;
        this.motivationalMessagePath = motivationalMessagePath;
        this.clientCoachMembershipFlx = clientCoachMembershipFlx;
    }

    public Date getTrackingDate() {
        return trackingDate;
    }

    public void setTrackingDate(Date currentDate) {
        this.trackingDate = currentDate;
    }

    public int getDietRate() {
        return dietRate;
    }

    public void setDietRate(int dietRate) {
        this.dietRate = dietRate;
    }

    public int getExerciseRate() {
        return exerciseRate;
    }

    public void setExerciseRate(int exerciseRate) {
        this.exerciseRate = exerciseRate;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getClientNote() {
        return clientNote;
    }

    public void setClientNote(String clientNote) {
        this.clientNote = clientNote;
    }

    public String getCoachNote() {
        return coachNote;
    }

    public void setCoachNote(String coachNote) {
        this.coachNote = coachNote;
    }

    public boolean isMotivationalMessageFlag() {
        return motivationalMessageFlag;
    }

    public void setMotivationalMessageFlag(boolean motivationalMessageFlag) {
        this.motivationalMessageFlag = motivationalMessageFlag;
    }

    public String getMotivationalMessagePath() {
        return motivationalMessagePath;
    }

    public void setMotivationalMessagePath(String motivationalMessagePath) {
        this.motivationalMessagePath = motivationalMessagePath;
    }

    public ClientCoachMembershipFlx getClientCoachMembershipFlx() {
        return clientCoachMembershipFlx;
    }

    public void setClientCoachMembershipFlx(ClientCoachMembershipFlx membershipFlx) {
        this.clientCoachMembershipFlx = membershipFlx;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

