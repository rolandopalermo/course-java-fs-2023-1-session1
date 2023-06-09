package com.rpconsulting.app.invoicing.repositories;

import com.rpconsulting.app.invoicing.repositories.entities.Invoice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	@Query("SELECT inv FROM Invoice inv WHERE inv.sequence =:sequence")
	Optional<Invoice> findFirstBySequence(String sequence);
}
