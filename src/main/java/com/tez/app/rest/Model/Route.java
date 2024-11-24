package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",length = 50)
    private String name;
    @Column(name = "origin", length = 50)
    private String origin;
    @Column(name = "passes",length = 50)
    private String passes; //irrelevant make it into url
    @Column(name = "destination", length = 50)
    private String destination;
    @Column(name = "discription",length = 255)
    private String discription;


    public void setDetails(String name,String origin, String passes, String destination, String discription) {
        this.name = name;
        this.origin = origin;
        this.passes = passes;
        this.destination = destination;
        this.discription = discription;
    }
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

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
