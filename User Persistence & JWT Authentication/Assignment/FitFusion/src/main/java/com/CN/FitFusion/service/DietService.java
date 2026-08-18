package com.CN.FitFusion.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.exception.DietNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.DietRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietService
{
    private final DietRepository dietRepository;
    private final UserService userService;

    public List<Diet> getAllDiets()
    {
        return dietRepository.findAll();
    }

    public Diet getDietById(long id)
    {
        return dietRepository.findById(id)
        .orElseThrow(() -> new DietNotFoundException("diet not foun"));
    }

    @Transactional
    public void addDietToUser(DietDto dto, long userId)
    {
        User user = userService.getUserById(userId);
        Diet diet = new Diet(null, dto.getName(), dto.getDescription(), user);
        user.getDiets().add(diet);
    }

    @Transactional
    public void updateDiet(DietDto dto, long id)
    {
        Diet diet = getDietById(id);
        diet.setName(dto.getName());
        diet.setDescription(dto.getDescription());
    }

    @Transactional
    public void deleteDiet(long id)
    {
        Diet diet = getDietById(id);
        User user = userService.getUserById(diet.getUser().getId());
        diet.setUser(null);
        user.getDiets().remove(diet);
        dietRepository.delete(diet);
    }

    public List<Diet> getAllUserDiets(long userId)
    {
        return dietRepository.findAllByUserId(userId);
    }
}