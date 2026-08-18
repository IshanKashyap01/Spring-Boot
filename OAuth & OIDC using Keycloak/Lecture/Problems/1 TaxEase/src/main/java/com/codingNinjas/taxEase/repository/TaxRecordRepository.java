package com.codingNinjas.taxEase.repository;

import com.codingNinjas.taxEase.model.TaxRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRecordRepository extends JpaRepository<TaxRecord, Long>
{
}