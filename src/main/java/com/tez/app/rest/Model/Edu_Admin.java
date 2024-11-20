package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EDU_ADMIN")
public class Edu_Admin extends Organization {

}
