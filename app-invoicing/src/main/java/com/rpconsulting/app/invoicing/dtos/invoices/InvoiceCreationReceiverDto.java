package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceCreationReceiverDto {

    private String name;
    private String number;//RUC,NIT,RFC
    private String telephoneNumber;
    private List<String> emails;

}
