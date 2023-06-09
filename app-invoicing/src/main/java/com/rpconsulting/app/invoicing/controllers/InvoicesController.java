package com.rpconsulting.app.invoicing.controllers;

import com.rpconsulting.app.invoicing.dtos.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.repositories.InvoiceRepository;
import com.rpconsulting.app.invoicing.services.InvoicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1.0/invoices")
@RequiredArgsConstructor
public class InvoicesController {

    private final InvoicesService invoicesService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public InvoiceCreationResponseDto create(@RequestBody InvoiceCreationRequestDto request) {
        return invoicesService.create(request);
    }

}
