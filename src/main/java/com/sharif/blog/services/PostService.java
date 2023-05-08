package com.sharif.blog.services;

import com.sharif.blog.entities.Post;
import com.sharif.blog.payloads.PostDto;
import com.sharif.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);

    PostDto findPostById(Integer postId);

    List<PostDto> findPostByUser(Integer userId);

    List<PostDto> findPostByCategory(Integer categoryId);

    List<PostDto> searchPosts(String keyword);

    void deletePost(Integer postId);


}
