package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "origin", length = 50)
    private String origin;
    @Column(name = "passes",length = 50)
    private String passes; //irrelevant make it into url
    @Column(name = "destination", length = 50)
    private String destination;
    @Column(name = "schedule", length = 50)
    private Integer schedule; //sch --> orgnazation -->
    @Column(name = "discription",length = 255)
    private String discription;

    public long getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getPasses() {
        return passes;
    }

    public String getDestination() {
        return destination;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public String getDiscription() {
        return discription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPasses(String passes) {
        this.passes = passes;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
