package com.rpconsulting.app.invoicing.repositories.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate issueDate;

    @Column
    private String sequence;

    @Column
    private String issuerName;

    @Column
    private String issuerNumber;

    @Column
    private String receiverName;

    @Column
    private String receiverNumber;

    @Column
    private String receiverTelephoneNumber;

    @Column
    private String receiverEmails;

    @Column
    private BigDecimal taxAmount;

    @Column
    private BigDecimal discount;

    @Column
    private BigDecimal total;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> details = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
