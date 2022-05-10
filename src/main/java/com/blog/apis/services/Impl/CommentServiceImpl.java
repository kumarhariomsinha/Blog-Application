package com.blog.apis.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.apis.entities.Comment;
import com.blog.apis.entities.Post;
import com.blog.apis.exceptions.ResourceNotFoundException;
import com.blog.apis.payloads.CommentDto;
import com.blog.apis.repositories.CommentRepo;
import com.blog.apis.repositories.PostRepo;
import com.blog.apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
	    Comment comment=this.modelMapper.map(commentDto, Comment.class);
	    comment.setPost(post);
	    Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
	Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));	
	this.commentRepo.delete(comment);
	}

//	@Override
//	public CommentDto getComment(int commentId) {
//		// TODO Auto-generated method stub
//		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId", commentId));
//
//		return this.modelMapper.map(comment, CommentDto.class);
//
//	}

}
