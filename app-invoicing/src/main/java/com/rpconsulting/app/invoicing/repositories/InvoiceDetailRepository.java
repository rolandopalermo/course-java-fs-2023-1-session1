package com.rpconsulting.app.invoicing.repositories;

import com.rpconsulting.app.invoicing.repositories.entities.InvoiceDetail;
import com.rpconsulting.app.invoicing.repositories.projections.InvoiceDetailProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {

    @Query(
            value = "SELECT invoiceDetail.id as id, invoiceDetail.code AS code, invoiceDetail.name AS name, invoiceDetail.unitPrice AS unitPrice, " +
            "invoice.id AS invoiceId, supplier.number AS supplierNumber, supplier.name AS supplierName " +
            "FROM InvoiceDetail invoiceDetail  " +
            "INNER JOIN Invoice invoice ON invoiceDetail.invoice.id = invoice.id " +
            "INNER JOIN Supplier supplier ON invoice.supplier.id = supplier.id " +
            "WHERE (:supplierNumber IS NULL OR supplier.number = :supplierNumber) AND " +
            "(:supplierName IS NULL OR supplier.name LIKE '%' || :supplierName || '%' )",
    countQuery = "SELECT COUNT(1) FROM InvoiceDetail invoiceDetail  " +
            "INNER JOIN Invoice invoice ON invoiceDetail.invoice.id = invoice.id " +
            "INNER JOIN Supplier supplier ON invoice.supplier.id = supplier.id " +
            "WHERE (:supplierNumber IS NULL OR supplier.number = :supplierNumber) AND " +
            "(:supplierName IS NULL OR supplier.name LIKE '%' || :supplierName || '%' )")
    Page<InvoiceDetailProjection> findAllDetails(String supplierNumber, String supplierName, Pageable pageable);

}
