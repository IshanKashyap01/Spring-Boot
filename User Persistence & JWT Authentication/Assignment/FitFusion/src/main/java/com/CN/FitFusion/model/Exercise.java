package com.CN.FitFusion.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise")
public class Exercise
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int sets;
    @Column
    private int reps;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-exercises")
    private User user;
}