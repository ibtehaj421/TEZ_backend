package com.tez.app.rest.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORP_ADMIN")
public class Corp_Admin extends Organization {

}
