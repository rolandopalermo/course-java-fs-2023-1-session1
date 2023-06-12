package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceResponseDto;

public interface InvoicesService {

    InvoiceCreationResponseDto create(InvoiceCreationRequestDto request);

    InvoiceResponseDto findById(Long id);

}
