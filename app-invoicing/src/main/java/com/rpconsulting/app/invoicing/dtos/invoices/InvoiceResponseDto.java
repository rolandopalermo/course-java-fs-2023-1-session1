package com.rpconsulting.app.invoicing.dtos.invoices;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class InvoiceResponseDto {

    private Long id;
    private String issueDate;
    private String sequence;
    private String issuerName;
    private String issuerNumber;
    private String receiverName;
    private String receiverNumber;
    private String receiverTelephoneNumber;
    private List<String> receiverEmails;
    private BigDecimal taxAmount;
    private BigDecimal discount;
    private BigDecimal total;
    private List<InvoiceDetailResponseDto> details;

}
