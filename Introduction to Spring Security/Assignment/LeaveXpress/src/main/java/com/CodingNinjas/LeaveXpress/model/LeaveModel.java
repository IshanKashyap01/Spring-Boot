package com.CodingNinjas.LeaveXpress.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "leave_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String type;
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column
    private String description;
    @Column
    private boolean isAccepted;
}
