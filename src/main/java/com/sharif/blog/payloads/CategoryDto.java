package com.sharif.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    private String categoryDescription;

    @NotEmpty
    @Size(min = 1,message = "Category id must be compulsory")
    private String categoryTitle;
}
