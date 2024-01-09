package com.mitron.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitron.onlinestore.domain.entities.Receipt;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {

    List <Receipt> findAllReceiptsByRecipient_UsernameOrderByIssuedOn(String customerName);
}
