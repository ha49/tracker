package com.example.tracker.entity;

import javax.persistence.*;

@Entity
@Table(name = "Documents")
public class DocumentFlx {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientId")
    private ClientFlx clientFlx;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    public DocumentFlx() {
    }

    public DocumentFlx(ClientFlx clientFlx, String name, String path) {
        this.clientFlx = clientFlx;
        this.name = name;
        this.path = path;
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

    public void setClient(ClientFlx clientFlx) {
        this.clientFlx = clientFlx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
