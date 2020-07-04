package com.adisutanto.sales.supplier;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(max = 40)
    @Column(name = "company_name")
    private String companyName;
    @Size(max = 50)
    @Column(name = "contact_name")
    private String contactName;
    @Size(max = 40)
    @Column(name = "contact_title")
    private String contactTitle;
    @Size(max = 40)
    private String city;
    @Size(max = 40)
    private String country;
    @Size(max = 30)
    private String phone;
    @Size(max = 30)
    private String fax;
}
