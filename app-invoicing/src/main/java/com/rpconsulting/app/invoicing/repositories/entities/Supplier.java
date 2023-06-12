package com.rpconsulting.app.invoicing.repositories.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String number;

    @OneToMany(mappedBy = "supplier")
    private List<Invoice> invoices = new ArrayList<>();

}
