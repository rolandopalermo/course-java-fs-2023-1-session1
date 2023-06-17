package com.rpconsulting.app.invoicing.controllers;

import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceDetailSummaryDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceResponseDto;
import com.rpconsulting.app.invoicing.services.InvoicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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
    public Page<InvoiceDetailSummaryDto> findAllDetails(
            Pageable pageable,
            @RequestParam(value = "supplier-number", required = false) String supplierNumber,
            @RequestParam(value = "supplier-name", required = false) String supplierName
    ) {
        log.info("Page = {}, Size = {}", pageable.getPageNumber(), pageable.getPageSize());
        return invoicesService.findAllDetails(supplierNumber, supplierName, pageable);
    }

}
