package com.sharif.blog.services.impl;

import com.sharif.blog.entities.Category;
import com.sharif.blog.entities.Post;
import com.sharif.blog.entities.User;
import com.sharif.blog.exceptions.ResourceNotFoundException;
import com.sharif.blog.payloads.PostDto;
import com.sharif.blog.payloads.PostResponse;
import com.sharif.blog.repository.CategoryRepo;
import com.sharif.blog.repository.PostRepo;
import com.sharif.blog.repository.UserRepo;
import com.sharif.blog.services.CategoryService;
import com.sharif.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User couldn't find with", "userId : ", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category couldn't find with", "CategoryId : ", categoryId));
        Post post = this.dtoToPost(postDto);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postRepo.save(post);
        return this.postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", " post id ", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=postRepo.save(post);
        return this.postToDto(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto findPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id unable to find with", "postId :", postId));
        return this.postToDto(post);
    }

    @Override
    public List<PostDto> findPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId with this ID", "doesntExist", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> allPosts = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        return allPosts;
    }

    @Override
    public List<PostDto> findPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with this ID", "doesntExist", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> allPosts = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        return allPosts;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id unable to find with", "postId :", postId));
        this.postRepo.delete(post);
    }

    public Post dtoToPost(PostDto postDto){
        Post post = this.modelMapper.map(postDto,Post.class);
        return post;
    }

    public PostDto postToDto(Post post){
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }
}
