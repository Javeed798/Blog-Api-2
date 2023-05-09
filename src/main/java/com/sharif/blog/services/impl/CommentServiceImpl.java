package com.sharif.blog.services.impl;

import com.sharif.blog.entities.Comment;
import com.sharif.blog.entities.Post;
import com.sharif.blog.exceptions.ResourceNotFoundException;
import com.sharif.blog.payloads.CommentDto;
import com.sharif.blog.repository.CommentRepo;
import com.sharif.blog.repository.PostRepo;
import com.sharif.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post is ", "not found with id", postId));
        Comment comment = dtoToComment(commentDto);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.commentToDTO(savedComment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer id) {
        Comment comment = this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment is ", "not found with id", id));
        comment.setContent(commentDto.getContent());
        Comment save = this.commentRepo.save(comment);
        return commentToDTO(save);
    }

    @Override
    public List<CommentDto> comments() {
        List<Comment> all = this.commentRepo.findAll();
        List<CommentDto> collect = all.stream().map(comment -> this.commentToDTO(comment)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto getCommentByCommentId(Integer id) {
        Comment comment = this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment is ", "not found with id", id));
        return this.commentToDTO(comment);
    }

    @Override
    public List<CommentDto> getCommentsByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentByPostId(Integer postId) {
        return null;
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment is ", "not found with id", id));
        this.commentRepo.delete(comment);
    }

    public CommentDto commentToDTO(Comment comment){
        CommentDto map = this.modelMapper.map(comment, CommentDto.class);
        return map;
    }

    public Comment dtoToComment(CommentDto commentDto){
        Comment map = this.modelMapper.map(commentDto, Comment.class);
        return map;
    }
}
