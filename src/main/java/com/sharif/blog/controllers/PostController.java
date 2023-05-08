package com.sharif.blog.controllers;

import com.sharif.blog.entities.Post;
import com.sharif.blog.payloads.ApiResponse;
import com.sharif.blog.payloads.PostDto;
import com.sharif.blog.payloads.PostResponse;
import com.sharif.blog.services.FileService;
import com.sharif.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping ("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto post = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
        PostDto postDto1 = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId){
        List<PostDto> postByUser = this.postService.findPostByUser(userId);
        return new ResponseEntity<>(postByUser,HttpStatus.OK);
    }

    @GetMapping("/posts/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable Integer categoryId){
        List<PostDto> postByCategory = this.postService.findPostByCategory(categoryId);
        return new ResponseEntity<>(postByCategory,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5",required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId",required = false) String sortBy

    ){
        PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize,sortBy);
        return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postById = this.postService.findPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully",true,new Date()),HttpStatus.OK);
    }

    @GetMapping("/posts/{title}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String title){
        List<PostDto> postDtos = this.postService.searchPosts(title);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }


    @PutMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId
    ) throws IOException {
        PostDto postDto = this.postService.findPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);

    }

    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
