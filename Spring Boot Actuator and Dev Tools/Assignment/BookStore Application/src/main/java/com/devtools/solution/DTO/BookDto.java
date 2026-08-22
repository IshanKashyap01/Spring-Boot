package com.devtools.solution.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto
{
    private String title;
    private String author;
    private Integer price;
    private Integer quantity;
}