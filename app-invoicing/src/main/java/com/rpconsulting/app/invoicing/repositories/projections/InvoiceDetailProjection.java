package com.rpconsulting.app.invoicing.repositories.projections;

import java.math.BigDecimal;

public interface InvoiceDetailProjection {

    //id
    Long getId();

    void setId(Long id);

    //code
    String getCode();

    void setCode(String code);

    //name
    String getName();

    void setName(String name);

    //unitPrice
    BigDecimal getUnitPrice();

    void setUnitPrice(BigDecimal unitPrice);

    //invoiceId
    Long getInvoiceId();

    void setInvoiceId(Long invoiceId);

    //supplierNumber
    String getSupplierNumber();

    void setSupplierNumber(String supplierNumber);

    //supplierName
    String getSupplierName();

    void setSupplierName(String supplierName);

}
