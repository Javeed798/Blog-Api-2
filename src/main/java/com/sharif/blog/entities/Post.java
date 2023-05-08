package com.sharif.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(length = 1000)
    private String content;

    @Column(nullable = false)
    private String title;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

    @ManyToOne
    private User user;

}
