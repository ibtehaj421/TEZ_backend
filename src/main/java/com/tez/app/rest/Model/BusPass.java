package com.tez.app.rest.Model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bus_pass")
public class BusPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long orgId; //specify which organization the pass is meant for.
    @Column(nullable = false)
    private long userId;
    @Column(nullable = true,length = 10)
    private String status;
    @Column(name = "issued")
    private LocalDate issued;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
}
