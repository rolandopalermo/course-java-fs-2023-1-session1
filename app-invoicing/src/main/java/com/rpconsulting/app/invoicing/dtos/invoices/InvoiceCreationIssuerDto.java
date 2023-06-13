package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Data;

@Data
public class InvoiceCreationIssuerDto {

    private String name;
    private String number;//RUC,NIT,RFC

}
