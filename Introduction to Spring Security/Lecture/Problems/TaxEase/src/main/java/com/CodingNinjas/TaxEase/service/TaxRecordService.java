package com.CodingNinjas.TaxEase.service;

import com.CodingNinjas.TaxEase.dto.TaxRecordDto;
import com.CodingNinjas.TaxEase.exception.TaxRecordNotFoundException;
import com.CodingNinjas.TaxEase.model.TaxRecord;
import com.CodingNinjas.TaxEase.repository.TaxRecordRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TaxRecordService
{
    /*
        This is the service class for TaxRecord, you need to complete the class by doing the following:

            a. Use appropriate annotations.
            b. Complete the methods given below.
            c. Autowire the necessary dependencies.
     */
    private final TaxRecordRepository repository;

    public TaxRecordService(TaxRecordRepository repository)
    {
        this.repository = repository;
    }
    // This is the service method for the api which allows user to get a tax Record by sending the record id
    public TaxRecord getTaxRecordById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new TaxRecordNotFoundException("tax record not found"));
    }


    // This is the service method for the api which allows user fetch all tax Records
    public List<TaxRecord> getAllRecords()
    {
        return repository.findAll();
    }


    // This is the service method for the api which allows user to create a tax Record by sending TaxRecordDto as the @ResponseBody
    public void createTaxRecord(TaxRecordDto dto)
    {
        repository.save(toTaxRecord(dto));
    }


    // This is the service method for the api which allows user to update a tax Record by sending the record id as a pathVariable and TaxRecordDto as a RequestBody
    @Transactional
    public void updateTaxRecord(TaxRecordDto taxRecordDto, Long id)
    {
        TaxRecord taxRecord = getTaxRecordById(id);
        updateFromDto(taxRecord, taxRecordDto);
        repository.save(taxRecord);
    }


    // This is the service method for the api which allows user to delete a tax Record by sending the record id as a pathVariable
    public void deleteTaxRecord(Long id)
    {
        repository.deleteById(id);
    }


    // This is the service method for the api which allows user to fetch all the tax Records by sending the username as a requestParam
    public List<TaxRecord> getRecordsByName(String userName)
    {
        return repository.findByUserName(userName);
    }


    // This is the service method for the api which allows user to approve a tax Record by sending the record id as a pathVariable
    @Transactional
    public void approveTaxFiling(Long id)
    {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecord.setFilingApproved(true);
    }


    // This is the service method for the api which allows user to reject a tax Record by sending the record id as a pathVariable
    @Transactional
    public void rejectTaxFiling(Long id)
    {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecord.setFilingApproved(false);
    }

    private TaxRecord toTaxRecord(TaxRecordDto dto)
    {
        return new TaxRecord(null, dto.getUserName(), dto.getTaxYear(), dto.getIncome(), dto.getDeductions(), false);
    }

    private void updateFromDto(TaxRecord taxRecord, TaxRecordDto dto)
    {
        taxRecord.setDeductions(dto.getDeductions());
        taxRecord.setIncome(dto.getIncome());
        taxRecord.setTaxYear(dto.getTaxYear());
        taxRecord.setUserName(dto.getUserName());
    }
}
