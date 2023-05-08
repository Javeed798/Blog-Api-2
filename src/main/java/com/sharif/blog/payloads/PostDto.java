package com.sharif.blog.payloads;

import lombok.*;
import java.util.Date;

@Data
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
