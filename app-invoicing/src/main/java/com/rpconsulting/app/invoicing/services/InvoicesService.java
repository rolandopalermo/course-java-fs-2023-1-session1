package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.InvoiceCreationResponseDto;

public interface InvoicesService {

    InvoiceCreationResponseDto create(InvoiceCreationRequestDto request);

}
