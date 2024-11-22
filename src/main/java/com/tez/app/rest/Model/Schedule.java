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
}
