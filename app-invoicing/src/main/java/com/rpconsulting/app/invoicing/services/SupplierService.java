package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationResponseDto;

public interface SupplierService {

    SupplierCreationResponseDto create(SupplierCreationRequestDto request);

}
