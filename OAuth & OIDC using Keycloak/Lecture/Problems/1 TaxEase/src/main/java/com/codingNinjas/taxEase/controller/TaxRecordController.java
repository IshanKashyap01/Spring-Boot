package com.codingNinjas.taxEase.controller;

import com.codingNinjas.taxEase.dto.TaxRecordDto;
import com.codingNinjas.taxEase.model.TaxRecord;
import com.codingNinjas.taxEase.service.TaxRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tax")
public class TaxRecordController
{
    private final TaxRecordService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public TaxRecord getTaxRecordById(@PathVariable Long id)
    {
        return service.getTaxRecordById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public List<TaxRecord> getALlTaxRecords()
    {
        return service.getAllRecords();      
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasRole('NORMAL')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTaxRecord(@RequestBody TaxRecordDto taxRecordDto, @PathVariable Long userId)
    {
        service.createTaxRecord(taxRecordDto, userId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaxRecord(@RequestBody TaxRecordDto taxRecordDto, @PathVariable Long id)
    {
        service.updateTaxRecord(taxRecordDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('NORMAL')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaxRecord(@PathVariable Long id)
    {
        service.deleteTaxRecord(id);
    }

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void approveTaxFiling(@PathVariable Long id)
    {
        service.approveTaxFiling(id);
    }

    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void rejectTaxFiling(@PathVariable Long id)
    {
        service.rejectTaxFiling(id);
    }
}