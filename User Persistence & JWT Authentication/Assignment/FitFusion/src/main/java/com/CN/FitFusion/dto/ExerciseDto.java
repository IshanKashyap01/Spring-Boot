package com.CN.FitFusion.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto
{
    private String name;
    private String description;
    private int sets;
    private int reps;
}