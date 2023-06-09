package com.rpconsulting.app.invoicing.dtos;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceCreationRequestDto {

    private String issueDate;
    private String sequence;
    private InvoiceIssuerDto issuer;
    private InvoiceReceiverDto receiver;
    private List<InvoiceDetailDto> details;

}
