package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceDetailResponseDto {

    private String code;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal qty;
    private BigDecimal taxAmount;
    private BigDecimal discount;
    private BigDecimal subTotal;

}
