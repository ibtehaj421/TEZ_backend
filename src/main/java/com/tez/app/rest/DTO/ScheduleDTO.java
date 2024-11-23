package com.tez.app.rest.DTO;

public class ScheduleDTO {
    public Integer id;
    public String from_and_to;
    public long orgID;
    public String timeOfDay;

    public void setDetails(Integer id, String from_and_to, long orgID, String timeOfDay) {
        this.id = id;
        this.from_and_to = from_and_to;
        this.orgID = orgID;
        this.timeOfDay = timeOfDay;
    }
}
