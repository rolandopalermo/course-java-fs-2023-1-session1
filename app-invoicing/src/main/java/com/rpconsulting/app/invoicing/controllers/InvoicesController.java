package com.rpconsulting.app.invoicing.controllers;

import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceResponseDto;
import com.rpconsulting.app.invoicing.repositories.projections.InvoiceDetailProjection;
import com.rpconsulting.app.invoicing.services.InvoicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("{invoice-id}")
    @ResponseStatus(code = HttpStatus.OK)
    public InvoiceResponseDto findById(@PathVariable("invoice-id") Long invoiceId) {
        return invoicesService.findById(invoiceId);
    }

    @GetMapping("details")
    @ResponseStatus(code = HttpStatus.OK)
    public List<InvoiceDetailProjection> findAllDetails() {
        return invoicesService.findAllDetails();
    }

}
