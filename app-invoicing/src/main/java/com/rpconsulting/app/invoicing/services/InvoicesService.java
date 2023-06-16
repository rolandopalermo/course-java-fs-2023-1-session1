package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceResponseDto;
import com.rpconsulting.app.invoicing.repositories.projections.InvoiceDetailProjection;

import java.util.List;

public interface InvoicesService {

    InvoiceCreationResponseDto create(InvoiceCreationRequestDto request);

    InvoiceResponseDto findById(Long id);

    List<InvoiceDetailProjection> findAllDetails();

}
