package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InvoicesListFilterDto {

    private String supplierNumber;
    private String supplierName;

}
