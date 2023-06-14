package com.rpconsulting.app.invoicing.repositories;

import com.rpconsulting.app.invoicing.repositories.entities.InvoiceDetail;
import com.rpconsulting.app.invoicing.repositories.projections.InvoiceDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {

    @Query("SELECT invoiceDetail.id, invoiceDetail.code, invoiceDetail.name, invoiceDetail.unitPrice, " +
            "invoice.id AS invoiceId, supplier.number AS supplierNumber, supplier.name AS supplierName " +
            "FROM InvoiceDetail invoiceDetail  " +
            "INNER JOIN Invoice invoice ON invoiceDetail.invoice.id = invoice.id " +
            "INNER JOIN Supplier supplier ON invoice.supplier.id = supplier.id")
    List<InvoiceDetailProjection> findAllDetails();

}
