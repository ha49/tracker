package com.example.tracker.entity;

import javax.persistence.*;


@Entity
public class LinkFlx {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "link")
    private String link;

    //(cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "coachId", nullable = false)
    private CoachFlx coachFlx;

    public LinkFlx(String description, String link, CoachFlx coachFlx) {
        this.description = description;
        this.link = link;
        this.coachFlx = coachFlx;
    }

    public LinkFlx() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public CoachFlx getCoachFlx() {
        return coachFlx;
    }

    public void setCoachFlx(CoachFlx coachFlx) {
        this.coachFlx = coachFlx;
    }

}
