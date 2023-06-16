package com.rpconsulting.app.invoicing.repositories.projections;

import java.math.BigDecimal;

public interface InvoiceDetailProjection {

    Long getId();

    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getName();

    void setName(String name);

    BigDecimal getUnitPrice();

    void setUnitPrice(BigDecimal unitPrice);

    Long getInvoiceId();

    void setInvoiceId(Long invoiceId);

    String getSupplierNumber();

    void setSupplierNumber(String supplierNumber);

    String getSupplierName();

    void setSupplierName(String supplierName);

}
