package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationResponseDto;
import com.rpconsulting.app.invoicing.repositories.SupplierRepository;
import com.rpconsulting.app.invoicing.repositories.entities.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierCreationResponseDto create(SupplierCreationRequestDto request) {
        return toResponse(supplierRepository.save(toEntity(request)));
    }

    private Supplier toEntity(SupplierCreationRequestDto request) {
        Supplier supplier = new Supplier();

        supplier.setName(request.getName());
        supplier.setNumber(request.getNumber());

        return supplier;
    }

    private SupplierCreationResponseDto toResponse(Supplier supplier) {
        SupplierCreationResponseDto response = new SupplierCreationResponseDto();

        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setNumber(supplier.getNumber());

        return response;
    }


}
