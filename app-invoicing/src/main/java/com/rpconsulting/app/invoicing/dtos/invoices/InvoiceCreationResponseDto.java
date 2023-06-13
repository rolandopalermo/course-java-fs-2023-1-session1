package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceCreationResponseDto {

    private Long id;
    private BigDecimal taxAmount;
    private BigDecimal discount;
    private BigDecimal total;

}
