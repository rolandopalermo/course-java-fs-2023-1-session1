package com.rpconsulting.app.invoicing.dtos.suppliers;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SupplierCreationRequestDto {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 13)
    private String number;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 300)
    private String name;

}
