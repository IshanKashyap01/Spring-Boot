package com.CN.FitFusion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.service.DietService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diet")
public class DietController
{
    private final DietService service;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Diet> getAllDiets()
    {
        return service.getAllDiets();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Diet getDietById(@PathVariable Long id)
    {
        return service.getDietById(id);
    }

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDietToUser(@RequestBody DietDto dto, @PathVariable Long userId)
    {
        service.addDietToUser(dto, userId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDiet(@RequestBody DietDto dto, @PathVariable Long id)
    {
        service.updateDiet(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDiet(@PathVariable Long id)
    {
        service.deleteDiet(id);
    }
}