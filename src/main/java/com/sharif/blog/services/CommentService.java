package com.sharif.blog.services;

import com.sharif.blog.payloads.CommentDto;
import com.sharif.blog.payloads.UserDto;
import org.apache.tomcat.jni.User;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    CommentDto updateComment(CommentDto commentDto, Integer id);

    List<CommentDto> comments();

    CommentDto getCommentByCommentId(Integer id);

    List<CommentDto> getCommentsByUserId(Integer userId);

    List<CommentDto> getCommentByPostId(Integer postId);

    void deleteComment(Integer id);
}
