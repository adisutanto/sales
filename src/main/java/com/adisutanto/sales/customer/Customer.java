package com.adisutanto.sales.customer;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 40)
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Size(max = 40)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 40)
    private String city;
    @Size(max = 40)
    private String country;
    @Size(max = 20)
    private String phone;
}
