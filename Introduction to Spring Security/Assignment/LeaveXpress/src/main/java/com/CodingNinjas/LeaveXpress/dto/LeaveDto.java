package com.CodingNinjas.LeaveXpress.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto
{
    private String type;
    private String startDate;
    private String endDate;
    private String description;
}
