package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceCreationRequestDto {

    private String issueDate;
    private String sequence;
    private InvoiceCreationIssuerDto issuer;
    private InvoiceCreationReceiverDto receiver;
    private List<InvoiceCreationDetailDto> details;

}
