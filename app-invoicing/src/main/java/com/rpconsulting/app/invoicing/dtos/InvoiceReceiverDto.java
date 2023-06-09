package com.rpconsulting.app.invoicing.dtos;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceReceiverDto {

    private String name;
    private String number;//RUC,NIT,RFC
    private String telephoneNumber;
    private List<String> emails;

}
