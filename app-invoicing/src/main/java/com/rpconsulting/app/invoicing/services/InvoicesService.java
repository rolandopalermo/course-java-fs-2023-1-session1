package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceDetailSummaryDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoicesListFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoicesService {

    InvoiceCreationResponseDto create(InvoiceCreationRequestDto request);

    InvoiceResponseDto findById(Long id);

    Page<InvoiceDetailSummaryDto> findAllDetails(InvoicesListFilterDto filters, Pageable pageable);

}
