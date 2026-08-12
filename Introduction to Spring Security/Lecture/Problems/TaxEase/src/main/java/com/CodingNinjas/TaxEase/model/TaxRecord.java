package com.CodingNinjas.TaxEase.model;

import javax.persistence.*;
import lombok.*;
/*
    This is the entity class, complete this class by doing the following:

    a. Add the required annotations for making this class an entity.
    b. Add the required lombok annotations for getter, setter and constructors
 */
@Entity
@Table(name = "tax_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String userName;
    @Column
    private String taxYear;
    @Column
    private int Income;
    @Column
    private int deductions;
    @Column
    private boolean isFilingApproved;
}