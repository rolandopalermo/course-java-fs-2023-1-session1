package com.rpconsulting.app.invoicing.controllers;

import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationResponseDto;
import com.rpconsulting.app.invoicing.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1.0/suppliers")
@RequiredArgsConstructor
public class SuppliersController {

    private final SupplierService supplierService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SupplierCreationResponseDto create(@Valid @RequestBody SupplierCreationRequestDto request) {
        return supplierService.create(request);
    }

}
