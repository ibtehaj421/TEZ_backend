package com.tez.app.rest.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 50)
    private String ModelName;
    @Column(unique = true, nullable = false)
    private String licPlateNumber;
    @Column(nullable = false,length = 10)
    private int capacity;
    @Column(nullable = true,length = 10)
    private long driverID;
    @Column(nullable = false)
    private long orgID;
    @Column(nullable = true,length = 5)
    private long routeId;

    public void setDetails(String ModelName, String licPlateNumber, int capacity, long orgID) {
        this.ModelName = ModelName;
        this.licPlateNumber = licPlateNumber;
        this.capacity = capacity;
        this.orgID = orgID; //specifies that the bus belongs to the following organiztion
    }
    public long getId() {
        return id;
    }

    public String getModelName() {
        return ModelName;
    }

    public String getLicPlateNumber() {
        return licPlateNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getDriverID() {
        return driverID;
    }

    public long getOrgID() {
        return orgID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public void setLicPlateNumber(String licPlateNumber) {
        this.licPlateNumber = licPlateNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDriverID(long driverID) {
        this.driverID = driverID;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getRouteId() {
        return routeId;
    }
}
