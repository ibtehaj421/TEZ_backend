package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String time; //time of the day, Morning Evening Night
    @Column(nullable = false)
    private String FromAndTo; //timings in am to am or am to pm or pm to pm or pm to am.
    @Column(nullable = false)
    private long orgID; //the organization the schedule belongs to.

    public Integer getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getFromAndTo() {
        return FromAndTo;
    }

    public long getOrgID() {
        return orgID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFromAndTo(String fromAndTo) {
        FromAndTo = fromAndTo;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }
}
