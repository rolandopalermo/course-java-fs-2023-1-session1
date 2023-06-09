package com.rpconsulting.app.invoicing.dtos;

import lombok.Data;

@Data
public class InvoiceIssuerDto {

    private String name;
    private String number;//RUC,NIT,RFC

}
