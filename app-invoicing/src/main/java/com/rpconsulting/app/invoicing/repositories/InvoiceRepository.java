package com.rpconsulting.app.invoicing.repositories;

import com.rpconsulting.app.invoicing.repositories.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("Select s from Invoice s where s.sequence = :sequence")
    Optional<Invoice> findBySecuence(String sequence);
}
