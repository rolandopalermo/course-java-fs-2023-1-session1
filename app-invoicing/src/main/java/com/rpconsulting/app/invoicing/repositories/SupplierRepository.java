package com.rpconsulting.app.invoicing.repositories;

import com.rpconsulting.app.invoicing.repositories.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    //@Query("SELECT supplier FROM Supplier supplier where supplier.number = :number LIMIT 1")
    @Query(value = "SELECT * FROM suppliers where number = :number LIMIT 1", nativeQuery = true)
    Optional<Supplier> findFirstByNumber(String number);

}
