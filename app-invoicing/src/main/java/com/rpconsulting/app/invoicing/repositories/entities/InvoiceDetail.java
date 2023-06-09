package com.rpconsulting.app.invoicing.repositories.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetail {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private BigDecimal unitPrice;

    @Column
    private BigDecimal qty;

    @Column
    private BigDecimal taxAmount;

    @Column
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

}
