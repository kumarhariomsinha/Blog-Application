package com.blog.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.entities.Comment;
import com.blog.apis.payloads.ApiResponse;
import com.blog.apis.payloads.CommentDto;
import com.blog.apis.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto createComment=this.commentService.createComment(commentDto, postId);
		//CommentDto commentDto1=this.commentService.getComment(commentDto.getId());
		//System.out.println("CommentDto "+commentDto1);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comments",true),HttpStatus.OK);
		
	}	
}









