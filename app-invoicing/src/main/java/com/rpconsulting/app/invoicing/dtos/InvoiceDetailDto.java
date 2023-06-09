package com.rpconsulting.app.invoicing.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDetailDto {

    private String code;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal qty;
    private BigDecimal taxAmount;
    private BigDecimal discount;

}
