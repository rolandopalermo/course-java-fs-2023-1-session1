package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.suppliers.SupplierCreationResponseDto;
import com.rpconsulting.app.invoicing.errors.exceptions.AlreadyExistsException;
import com.rpconsulting.app.invoicing.repositories.SupplierRepository;
import com.rpconsulting.app.invoicing.repositories.entities.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierCreationResponseDto create(SupplierCreationRequestDto request) {
        Optional<Supplier> existingSupplier = supplierRepository.findFirstByNumber(request.getNumber());
        if (existingSupplier.isPresent()) {
            throw new AlreadyExistsException(format("La empresa con número de R.U.C. {0} ya existe", request.getNumber()));
        }

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
