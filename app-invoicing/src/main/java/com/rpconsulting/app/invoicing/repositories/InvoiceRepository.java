package com.rpconsulting.app.invoicing.repositories;

import com.rpconsulting.app.invoicing.repositories.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT invoice FROM Invoice invoice where invoice.sequence = :sequence")
    Optional<Invoice> findFirstBySequence(String sequence);

    @Query("SELECT invoice FROM Invoice invoice where invoice.issuerNumber = :issuerNumber AND invoice.sequence = :sequence")
    Optional<Invoice> findFirstBySequence(String issuerNumber, String sequence);

}
