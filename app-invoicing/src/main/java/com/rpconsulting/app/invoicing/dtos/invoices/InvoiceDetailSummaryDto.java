package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDetailSummaryDto {

    private Long id;
    private String code;
    private Long invoiceId;
    private String supplierNumber;
    private String supplierName;
    private BigDecimal unitPrice;
    private String name;

}
