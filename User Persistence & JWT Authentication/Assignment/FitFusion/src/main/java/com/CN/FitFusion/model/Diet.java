package com.CN.FitFusion.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diet")
public class Diet
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-diets")
    private User user;
}