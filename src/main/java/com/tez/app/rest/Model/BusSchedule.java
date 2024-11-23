package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
public class BusSchedule {

    @EmbeddedId
    private BusScheduleId id;

    @ManyToOne
    @MapsId("busId")
    @JoinColumn(name = "busid", referencedColumnName = "id")
    private Bus bus;

    @ManyToOne
    @MapsId("scheduleId")
    @JoinColumn(name = "scheduleid", referencedColumnName = "id")
    private Schedule schedule;


    public BusScheduleId getId() {
        return id;
    }

    public void setId(BusScheduleId id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
