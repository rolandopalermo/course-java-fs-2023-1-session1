package com.rpconsulting.app.invoicing.dtos.suppliers;

import lombok.Data;

@Data
public class SupplierCreationResponseDto {

    private Long id;
    private String number;
    private String name;

}
