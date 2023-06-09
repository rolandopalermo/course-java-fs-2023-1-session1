package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.repositories.entities.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvoicesService {

    InvoiceCreationResponseDto create(InvoiceCreationRequestDto request);

}
