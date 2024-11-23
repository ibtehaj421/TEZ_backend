package com.tez.app.rest.Model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BusScheduleId implements Serializable {
    private Long busId;
    private Long scheduleId;

    public Long getBusID() {
        return busId;
    }

    public Long getScheduleID() {
        return scheduleId;
    }

    public void setBusID(Long busID) {
        this.busId = busID;
    }

    public void setScheduleID(Long scheduleID) {
        this.scheduleId = scheduleID;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusScheduleId that = (BusScheduleId) o;
        return busId.equals(that.busId) && scheduleId.equals(that.scheduleId);
    }

    @Override
    public int hashCode() {
        return 31 * busId.hashCode() + scheduleId.hashCode();
    }
}
