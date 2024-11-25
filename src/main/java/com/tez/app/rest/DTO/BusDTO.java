package com.tez.app.rest.DTO;


import com.tez.app.rest.Model.Bus;

public class BusDTO {
    public long id;
    public String model;
    public String licNum;
    public int capacity;
    public Long driverID;
    public long orgID;
    public Long route;
    public void setDetails(String model, String licNum, int capacity, Long driverID, long orgID){
        this.model = model;
        this.licNum = licNum;
        this.capacity = capacity;
        this.driverID = driverID;
        this.orgID = orgID;
    }
    public void extractBus(Bus req){
        id = req.getId();
        model = req.getModelName();
        licNum = req.getLicPlateNumber();
        capacity = req.getCapacity();
        driverID = req.getDriverID();
        orgID = req.getOrgID();
    }
}

