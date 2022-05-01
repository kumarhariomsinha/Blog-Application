package com.blog.apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.config.AppConstants;
import com.blog.apis.entities.Post;
import com.blog.apis.payloads.ApiResponse;
import com.blog.apis.payloads.PostDto;
import com.blog.apis.payloads.PostResponse;
import com.blog.apis.repositories.PostRepo;
import com.blog.apis.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private PostRepo postrepo;
	//create the post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		
		PostDto createdPost =this.postService.createPost(postDto, userId, categoryId) ;	
	
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//get Post by users
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity <List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get Post by category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto>posts=this.postService.getPostsBycategory(categoryId);
		return new ResponseEntity <List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get all Posts
	  
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
		@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
		@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
		@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy	
			){
    	PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy);
    	
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		}
	
	//get PostBy Id
	
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
    	PostDto posts=this.postService.getPostById(postId);
    	
		return new ResponseEntity <PostDto>(posts,HttpStatus.OK);
		}
	//delete Post by Id
	@DeleteMapping("posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted",true);
	}
	//update the post
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto ,@PathVariable Integer postId) {
		PostDto updatedPost=this.postService.updatePost(postdto, postId);
		return new ResponseEntity <PostDto>(updatedPost,HttpStatus.OK);
	}
	//search
	@GetMapping("posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> serachPostByTitle(
			@PathVariable("keywords") String keywords){
		List<PostDto> result=this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	
}
