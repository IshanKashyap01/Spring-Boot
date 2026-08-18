package com.CN.FitFusion.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.exception.ExerciseNotFoundException;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseService
{
    private final ExerciseRepository repository;
    private final UserService service;

    public List<Exercise> getAllExercises()
    {
        return repository.findAll();
    }

    public Exercise getExerciseById(long id)
    {
        return repository.findById(id)
        .orElseThrow(() -> new ExerciseNotFoundException("exercise not found"));
    }

    @Transactional
    public void addExerciseToUser(ExerciseDto dto, long userId)
    {
        User user = service.getUserById(userId);
        Exercise exercise = new Exercise(null, dto.getName(), dto.getDescription(), dto.getSets(), dto.getReps(), user);
        user.getExerciseList().add(exercise);
    }

    @Transactional
    public void updateExercise(ExerciseDto dto, long id)
    {
        Exercise exercise = getExerciseById(id);
        exercise.setName(dto.getName());
        exercise.setDescription(dto.getDescription());
        exercise.setSets(dto.getSets());
        exercise.setReps(dto.getReps());
    }

    @Transactional
    public void deleteExercise(long id)
    {
        Exercise exercise = getExerciseById(id);
        User user = service.getUserById(exercise.getUser().getId());
        exercise.setUser(null);
        user.getExerciseList().remove(exercise);
        repository.delete(exercise);
    }

    public List<Exercise> getAllUserExercises(long userId)
    {
        return repository.findAllByUserId(userId);
    }
}